

package cz.cvut.fit.jcool.benchmark.method;

import java.util.Arrays;

import org.ytoh.configurations.annotations.Component;
import org.ytoh.configurations.annotations.Property;
import org.ytoh.configurations.annotations.Range;

import cz.cvut.fit.jcool.benchmark.stopcondition.SimpleStopCondition;
import cz.cvut.fit.jcool.core.Consumer;
import cz.cvut.fit.jcool.core.ObjectiveFunction;
import cz.cvut.fit.jcool.core.OptimizationMethod;
import cz.cvut.fit.jcool.core.Point;
import cz.cvut.fit.jcool.core.StopCondition;
import cz.cvut.fit.jcool.core.ValuePoint;
import cz.cvut.fit.jcool.core.ValuePointListTelemetry;
import cz.cvut.fit.jcool.utils.MachineAccuracy;

/**
 * Created by IntelliJ IDEA.
 * User: Carnuss
 * Date: 25.1.12
 * Time: 11:54
 * To change this template use File | Settings | File Templates.
 */
@Component(name = "Artificial Bee Colony")
public class ABCMethod implements OptimizationMethod<ValuePointListTelemetry> {


    /* Control Parameters of ABC algorithm*/
    @Property(name = "Colony size")
    @Range(from = 2, to = Integer.MAX_VALUE)
    private int colonySize =10; /* The number of colony size (employed bees+onlooker bees)*/
    private int foodNumber; /*The number of food sources equals the half of the colony size*/
    @Property(name = "Limit")
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int limit;  /*A food source which could not be improved through "limit" trials is abandoned by its employed bee*/
    @Property(name = "Inter cycles")
    @Range(from = -1, to = Integer.MAX_VALUE)
    private int maxCycle = 300; /*The number of cycles for foraging {a stopping criteria}*/

    /* Problem specific variables*/
    private int d; /*The number of parameters of the problem to be optimized*/
    @Property(name = "Parameter Minimum")
    @Range(from = -Double.MAX_VALUE, to = Double.MAX_VALUE)
    private double min = -10.0; /*lower bound of the parameters. */
    @Property(name = "Parameter Maximum")
    @Range(from = -Double.MAX_VALUE, to = Double.MAX_VALUE)
    private double max = 10.0; /*upper bound of the parameters. min and max can be defined as arrays for the problems of which parameters have different bounds*/


    private ObjectiveFunction function;
    private double foods[][];        /*foods is the population of food sources. Each row of foods matrix is a vector holding d parameters to be optimized. The number of rows of foods matrix equals to the foodNumber*/
    private double f[];        /*f is a vector holding objective function values associated with food sources */
    private double fitness[];      /*fitness is a vector holding fitness (quality) values associated with food sources*/
    private double trial[];         /*trial is a vector holding trial numbers through which solutions can not be improved*/
    private double prob[];          /*prob is a vector holding probabilities of food sources (solutions) to be chosen*/
    private double solution[];            /*New solution (neighbour) produced by v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) j is a randomly chosen parameter and k is a randomlu chosen solution different from i*/


    private double objValSol;              /*Objective function value of new solution*/
    private double fitnessSol;              /*Fitness value of new solution*/
    private int neighbour, param2change;                   /*param2change corrresponds to j, neighbour corresponds to k in equation v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij})*/

    private double globalMin;                       /*Optimum solution obtained by ABC algorithm*/
    private double globalParams[];                   /*Parameters of the optimum solution*/
    private Point solutionPoint;
    private ValuePointListTelemetry telemetry;
    private SimpleStopCondition stopCondition;
    private Consumer<? super ValuePointListTelemetry> consumer;
    private double r; /*a random number in the range [0,1)*/
    ValuePoint minimum;
    ValuePoint[] coordinates;
    public ABCMethod() {
        this.stopCondition = new SimpleStopCondition();
        this.stopCondition.init(Double.POSITIVE_INFINITY, MachineAccuracy.EPSILON, MachineAccuracy.SQRT_EPSILON, 20);
        this.telemetry = new ValuePointListTelemetry();
    }
    /**Variables are initialized in the range [min,max].
     *  If each parameter has different range, use arrays min[j], max[j] instead of min and max
     *  Counters of food sources are also initialized in this function
     *  * @param index*/
    void initPopulation(int index)
    {
        int j;
        for (j=0;j< d;j++)
        {
            r = (   (double)Math.random()*32767 / ((double)32767+(double)(1)) );
            foods[index][j]=r*(max - min)+ min;
            solution[j]= foods[index][j];
        }
        solutionPoint= Point.at(solution);
        f[index]=function.valueAt(solutionPoint);
        coordinates[index]= ValuePoint.at(solutionPoint, f[index]);
        fitness[index]= calculateFitness(f[index]);
        trial[index]=0;
    }
    /**Fitness function
     * @param fun
     *@return
     */
    double calculateFitness(double fun)
    {
        double result=0;
        if(fun>=0)
        {
            result=1/(fun+1);
        }
        else
        {

            result=1+Math.abs(fun);
        }
        return result;
    }
    /**All food sources are initialized
     * @param function*/
    public void init(ObjectiveFunction function) {
        this.function=function;
        d =function.getDimension();
        foodNumber = colonySize /2;
        limit=d*foodNumber;
        coordinates = new ValuePoint[foodNumber+1];
        foods =new double[foodNumber][d];        /*foods is the population of food sources. Each row of foods matrix is a vector holding d parameters to be optimized. The number of rows of foods matrix equals to the foodNumber*/
        f=new double[foodNumber];        /*f is a vector holding objective function values associated with food sources */
        fitness=new double[foodNumber];      /*fitness is a vector holding fitness (quality) values associated with food sources*/
        trial=new double[foodNumber];         /*trial is a vector holding trial numbers through which solutions can not be improved*/
        prob=new double[foodNumber];          /*prob is a vector holding probabilities of food sources (solutions) to be chosen*/
        solution=new double[d];
        globalParams =new double[d];                   /*Parameters of the optimum solution*/

        int i;
        for(i=0;i< foodNumber;i++)
        {
            initPopulation(i);
        }
        globalMin =f[0];
        stopCondition.setInitialValue(globalMin);
        for(i=0;i< d;i++)
            globalParams[i]= foods[0][i];
    }
    /**The best food source is memorized
     */
    void memorizeBestSource()
    {
        int i,j;

        for(i=0;i< foodNumber;i++)
        {
            if (f[i]< globalMin)
            {
                globalMin =f[i];
                coordinates[foodNumber]= coordinates[i];  //tady zmena protoze algoritmus zahazuje pozici nejlepsiho reseni a pamatuje si jen funkcni hodnotu je treba kvuli telemetrii tuto pozici uchovavat
                stopCondition.setValue(globalMin);
                for(j=0;j< d;j++)
                    globalParams[j]= foods[i][j];
            }
        }
    }
    /**Employed Bee Phase,in which employed bee are tested nectar quantity*/
    void sendEmployedBees()
    {
        int i,j;

        for (i=0;i< foodNumber;i++)
        {
            /*The parameter to be changed is determined randomly*/
            r = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
            param2change=(int)(r* d);

            /*A randomly chosen solution is used in producing a mutant solution of the solution i*/
            r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
            neighbour=(int)(r* foodNumber);

            for(j=0;j< d;j++)
                solution[j]= foods[i][j];

            /*v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) */
            r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
            solution[param2change]= foods[i][param2change]+(foods[i][param2change]- foods[neighbour][param2change])*(r-0.5)*2;

            /*if generated parameter value is out of boundaries, it is shifted onto the boundaries*/
            if (solution[param2change]< min)
                solution[param2change]= min;
            if (solution[param2change]> max)
                solution[param2change]= max;
            solutionPoint= Point.at(solution);
            objValSol =function.valueAt(solutionPoint);

            fitnessSol = calculateFitness(objValSol);

            /*a greedy selection is applied between the current solution i and its mutant*/
            if (fitnessSol >fitness[i])
            {

                /*If the mutant solution is better than the current solution i, replace the solution with the mutant and reset the trial counter of solution i*/
                trial[i]=0;
                for(j=0;j< d;j++)
                    foods[i][j]=solution[j];
                f[i]= objValSol;
                minimum= ValuePoint.at(solutionPoint, objValSol);
                coordinates[i]=minimum;
                fitness[i]= fitnessSol;
            }
            else
            {   /*if the solution i can not be improved, increase its trial counter*/
                trial[i]=trial[i]+1;
            }


        }

        /*end of employed bee phase*/

    }
    /** A food source is chosen with the probability which is proportional to its quality*/
    /*Different schemes can be used to calculate the probability values*/
    /*For example prob(i)=fitness(i)/sum(fitness)*/
    /*or in a way used in the method below prob(i)=a*fitness(i)/max(fitness)+b*/
    /*probability values are calculated by using fitness values and normalized by dividing maximum fitness value*/
    void calculateProbabilities()
    {
        int i;
        double maxfit;
        maxfit=fitness[0];
        for (i=1;i< foodNumber;i++)
        {
            if (fitness[i]>maxfit)
                maxfit=fitness[i];
        }

        for (i=0;i< foodNumber;i++)
        {
            prob[i]=(0.9*(fitness[i]/maxfit))+0.1;
        }

    }
    /*onlooker Bee Phase, in which onlooker bee are tested nectar quantity*/

    void sendOnlookerBees()
    {

        int i,j,t;
        i=0;
        t=0;

        while(t< foodNumber)
        {

            r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
            if(r<prob[i]) /*choose a food source depending on its probability to be chosen*/
            {
                t++;

                /*The parameter to be changed is determined randomly*/
                r = ((double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
                param2change=(int)(r* d);

                /*A randomly chosen solution is used in producing a mutant solution of the solution i*/
                r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
                neighbour=(int)(r* foodNumber);

                /*Randomly selected solution must be different from the solution i*/
                while(neighbour == i)
                {
                    //System.out.println(Math.random()*32767+"  "+32767);
                    r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
                    neighbour=(int)(r* foodNumber);
                }
                for(j=0;j< d;j++)
                    solution[j]= foods[i][j];

                /*v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) */
                r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
                solution[param2change]= foods[i][param2change]+(foods[i][param2change]- foods[neighbour][param2change])*(r-0.5)*2;

                /*if generated parameter value is out of boundaries, it is shifted onto the boundaries*/
                if (solution[param2change]< min)
                    solution[param2change]= min;
                if (solution[param2change]> max)
                    solution[param2change]= max;
                solutionPoint= Point.at(solution);
                objValSol =function.valueAt(solutionPoint);
                fitnessSol = calculateFitness(objValSol);

                /*a greedy selection is applied between the current solution i and its mutant*/
                if (fitnessSol >fitness[i])
                {
                    /*If the mutant solution is better than the current solution i, replace the solution with the mutant and reset the trial counter of solution i*/
                    trial[i]=0;
                    for(j=0;j< d;j++)
                        foods[i][j]=solution[j];
                    f[i]= objValSol;
                    minimum= ValuePoint.at(solutionPoint, objValSol);
                    coordinates[i]=minimum;
                    fitness[i]= fitnessSol;
                }
                else
                {   /*if the solution i can not be improved, increase its trial counter*/
                    trial[i]=trial[i]+1;
                }
            } /*if */
            i++;
            if (i== foodNumber -1)
                i=0;
        }/*while*/

        /*end of onlooker bee phase     */
    }
    /**determine the food sources whose trial counter exceeds the "limit" value. In Basic ABC, only one scout is allowed to occur in each cycle*/
    void sendScoutBees()
    {
        int maxTrialIndex,i;
        maxTrialIndex=0;
        for (i=1;i< foodNumber;i++)
        {
            if (trial[i]>trial[maxTrialIndex])
                maxTrialIndex=i;
        }
        if(trial[maxTrialIndex]>=limit)
        {
            initPopulation(maxTrialIndex);
        }
    }

    public StopCondition[] getStopConditions() {
        return new StopCondition[]{stopCondition};
    }

    public void optimize() {
        int iter=0;
        int run=0;
        int j=0;

            memorizeBestSource();
            for (iter=0;iter<maxCycle;iter++)
            {
                sendEmployedBees();
                calculateProbabilities();
                sendOnlookerBees();
                memorizeBestSource();
                sendScoutBees();
            }
            telemetry= new ValuePointListTelemetry(Arrays.asList(coordinates));
            consumer.notifyOf(this);
            stopCondition.setValue(globalMin);
            for(j=0;j< d;j++)
            {
            }

    }

    public void addConsumer(Consumer<? super ValuePointListTelemetry> consumer) {
        this.consumer=consumer;
    }

    public ValuePointListTelemetry getValue() {
        return telemetry;
    }
    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
    public int getColonySize() {
        return colonySize;
    }

    public void setColonySize(int colonySize) {
        this.colonySize = colonySize;
    }
    public int getMaxCycle() {
        return maxCycle;
    }

    public void setMaxCycle(int maxCycle) {
        this.maxCycle = maxCycle;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
