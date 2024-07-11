/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.felk.cig.jcool.ui;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.ytoh.configurations.context.DefaultContext;
import org.ytoh.configurations.context.DefaultPublishingContext;
import org.ytoh.configurations.context.PublishingContext;

import cz.cvut.fit.cig.jcool.experiment.TelemetryVisualization;
import cz.cvut.fit.jcool.benchmark.function.AckleyFunction;
import cz.cvut.fit.jcool.benchmark.function.BealeFunction;
import cz.cvut.fit.jcool.benchmark.function.BohachevskyFunction;
import cz.cvut.fit.jcool.benchmark.function.BoothFunction;
import cz.cvut.fit.jcool.benchmark.function.BraninFunction;
import cz.cvut.fit.jcool.benchmark.function.ColvilleFunction;
import cz.cvut.fit.jcool.benchmark.function.DeJongParabolaFunction;
import cz.cvut.fit.jcool.benchmark.function.DixonPriceFunction;
import cz.cvut.fit.jcool.benchmark.function.EasomFunction;
import cz.cvut.fit.jcool.benchmark.function.GoldsteinPriceFunction;
import cz.cvut.fit.jcool.benchmark.function.GriewangkFunction;
import cz.cvut.fit.jcool.benchmark.function.HartmannFunction;
import cz.cvut.fit.jcool.benchmark.function.HimmelblauFunction;
import cz.cvut.fit.jcool.benchmark.function.HumpFunction;
import cz.cvut.fit.jcool.benchmark.function.LangermannFunction;
import cz.cvut.fit.jcool.benchmark.function.Levy3;
import cz.cvut.fit.jcool.benchmark.function.Levy5;
import cz.cvut.fit.jcool.benchmark.function.LevyFunction;
import cz.cvut.fit.jcool.benchmark.function.MatyasFunction;
import cz.cvut.fit.jcool.benchmark.function.MichalewiczFunction;
import cz.cvut.fit.jcool.benchmark.function.PermFunction;
import cz.cvut.fit.jcool.benchmark.function.PowellFunction;
import cz.cvut.fit.jcool.benchmark.function.PowerSumFunction;
import cz.cvut.fit.jcool.benchmark.function.RanaFunction;
import cz.cvut.fit.jcool.benchmark.function.RastriginFunction;
import cz.cvut.fit.jcool.benchmark.function.RosenbrockFunction;
import cz.cvut.fit.jcool.benchmark.function.SchwefelFunction;
import cz.cvut.fit.jcool.benchmark.function.ShekelFunction;
import cz.cvut.fit.jcool.benchmark.function.ShubertFunction;
import cz.cvut.fit.jcool.benchmark.function.SphereFunction;
import cz.cvut.fit.jcool.benchmark.function.TestFunction2Wrapper;
import cz.cvut.fit.jcool.benchmark.function.TridFunction;
import cz.cvut.fit.jcool.benchmark.function.WhitleyFunction;
import cz.cvut.fit.jcool.benchmark.function.ZakharovFunction;
import cz.cvut.fit.jcool.benchmark.method.ABCMethod;
import cz.cvut.fit.jcool.benchmark.method.ant.aaca.AACAMethod;
import cz.cvut.fit.jcool.benchmark.method.ant.aco.ACOMethod;
import cz.cvut.fit.jcool.benchmark.method.ant.api.APIMethod;
import cz.cvut.fit.jcool.benchmark.method.ant.caco.CACOMethod;
import cz.cvut.fit.jcool.benchmark.method.ant.daco.DACOMethod;
import cz.cvut.fit.jcool.benchmark.method.cmaes.IPOPCMAESMethod;
import cz.cvut.fit.jcool.benchmark.method.cmaes.PureCMAESMethod;
import cz.cvut.fit.jcool.benchmark.method.cmaes.SigmaMeanIPOPCMAESMethod;
import cz.cvut.fit.jcool.benchmark.method.cmaesold.CMAESMethod;
import cz.cvut.fit.jcool.benchmark.method.direct.DirectMethod;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.DistanceFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.EvolutionaryOptimizationMethod;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.FitnessFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.FunctionEvaluator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.IndividualFactory;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.PopulationFactory;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.RepresentationFactory;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.ReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.SelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.evaluators.ParallelFunctionEvaluator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.evaluators.SimpleFunctionEvaluator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.factories.individual.SimpleIndividualFactory;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.factories.population.SimplePopulationFactory;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.factories.representation.BitSetGenotypeRepresentationFactory;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.factories.representation.SimpleGenotypeRepresentationFactory;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.factories.representation.SimplePhenotypeRepresentationFactory;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.derating.DeratingFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.derating.ExponentialDeratingFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.derating.PowerLawDeratingFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.distance.EuclideanDistanceFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.distance.HammingDistanceFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.distance.ManhattanDistanceFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.fitness.SimpleFitnessFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.fitness.SimpleSharingFitnessFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.fitness.SimpleSuppressingFitnessFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.fitness.SuppressingFitnessFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.sharing.SharingFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.functions.sharing.SimpleSharingFunction;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.methods.DeterministicCrowdingEvolutionaryMethod;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.methods.DifferentialEvolutionEvolutionaryMethod;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.methods.EvolutionaryStrategiesEvolutionaryMethod;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.methods.GeneticAlgorithmEvolutionaryMethod;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.methods.ParallelHillClimbingEvolutionaryMethod;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.methods.SequentialNichingEvolutionaryMethod;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.GenotypeHillClimbingMutationOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.GenotypeMutationReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.GenotypeNPointCrossoverReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.GenotypeUniformCrossoverReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.HillClimbingMutationOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.PhenotypeAdaptiveGaussianMutationReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.PhenotypeBlendingCrossoverReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.PhenotypeDifferentialEvolutionReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.PhenotypeGaussianMutationReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.PhenotypeHillClimbingMutationOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.PhenotypeNPointCrossoverReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.reproduction.PhenotypeUniformCrossoverReproductionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.BinaryTournamentSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.DeterministicCrowdingSurvivalSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.DifferentialEvolutionParentSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.DifferentialEvolutionSurvivalSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.FitnessProportionateSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.PermutationSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.RankProportionateSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.StochasticUniversalSamplingSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.TruncationSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.UniformDeterministicSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.evolutionary.operators.selection.UniformStochasticSelectionOperator;
import cz.cvut.fit.jcool.benchmark.method.genetic.de.DifferentialEvolutionMethod;
import cz.cvut.fit.jcool.benchmark.method.genetic.de.PALDifferentialEvolutionMethod;
import cz.cvut.fit.jcool.benchmark.method.genetic.pbil.PBILMethod;
import cz.cvut.fit.jcool.benchmark.method.genetic.sade.SADEMethod;
import cz.cvut.fit.jcool.benchmark.method.gradient.cg.ConjugateGradientMethod;
import cz.cvut.fit.jcool.benchmark.method.gradient.lm.LevenbergMarquardtMethod;
import cz.cvut.fit.jcool.benchmark.method.gradient.qn.QuasiNewtonMethod;
import cz.cvut.fit.jcool.benchmark.method.gradient.sd.SteepestDescentMethod;
import cz.cvut.fit.jcool.benchmark.method.hgapso.HGAPSOMethod;
import cz.cvut.fit.jcool.benchmark.method.orthogonalsearch.OrthogonalSearchMethod;
import cz.cvut.fit.jcool.benchmark.method.powell.PowellMethod;
import cz.cvut.fit.jcool.benchmark.method.pso.PSOMethod;
import cz.cvut.fit.jcool.benchmark.method.random.RandomMethod;
import cz.cvut.fit.jcool.benchmark.util.SimpleRandomGenerator;
import cz.cvut.fit.jcool.core.Function;
import cz.cvut.fit.jcool.core.OptimizationMethod;
import cz.cvut.fit.jcool.core.RandomGenerator;
import cz.cvut.fit.jcool.solver.Solver;
import cz.cvut.fit.jcool.solver.SolverFactory;

/**
 * @author ytoh
 */
public class ConfigurationContextFactoryBean extends AbstractFactoryBean {

    @Override
    public Class getObjectType() {
        return PublishingContext.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Object createInstance() throws Exception {
        PublishingContext configurationContext = new DefaultPublishingContext(new DefaultContext());
        configurationContext.register(Function.class,
                Arrays.asList(
                        new AckleyFunction(),
                        new BealeFunction(),
                        new BohachevskyFunction(),
                        new BoothFunction(),
                        new BraninFunction(),
                        new ColvilleFunction(),
                        new DeJongParabolaFunction(),
                        new DixonPriceFunction(),
                        new EasomFunction(),
                        new GoldsteinPriceFunction(),
                        new GriewangkFunction(),
                        new HartmannFunction(),
                        new HimmelblauFunction(),
                        new TestFunction2Wrapper(), //Honza's Test Function
                        new HumpFunction(),
                        new LangermannFunction(),
                        new LevyFunction(),
                        new Levy3(),
                        new Levy5(),
                        new MatyasFunction(),
                        new MichalewiczFunction(),
                        new PermFunction(),
                        new PowellFunction(),
                        new PowerSumFunction(),
                        new RanaFunction(),
                        new RastriginFunction(),
                        new RosenbrockFunction(),
                        new SchwefelFunction(),
                        new ShekelFunction(),
                        new ShubertFunction(),
                        new SphereFunction(),
                        new TridFunction(),
                        new WhitleyFunction(),
                        new ZakharovFunction()
                ), "functions");

        GeneticAlgorithmEvolutionaryMethod geneticAlgorithmEvolutionaryMethod = new GeneticAlgorithmEvolutionaryMethod();
        DifferentialEvolutionEvolutionaryMethod differentialEvolutionEvolutionaryMethod = new DifferentialEvolutionEvolutionaryMethod();
        EvolutionaryStrategiesEvolutionaryMethod evolutionaryStrategiesEvolutionaryMethod = new EvolutionaryStrategiesEvolutionaryMethod();

        configurationContext.register(OptimizationMethod.class,
                Arrays.asList(
                        new AACAMethod(),
                        new ABCMethod(),
                        new ACOMethod(),
                        new APIMethod(),
                        new CACOMethod(),
                        new CMAESMethod(),
                        new ConjugateGradientMethod(),
                        new DACOMethod(),
                        new DifferentialEvolutionMethod(),
                        new DirectMethod(),
                        new HGAPSOMethod(),
                        new LevenbergMarquardtMethod(),
                        new OrthogonalSearchMethod(),
                        new PALDifferentialEvolutionMethod(),
                        new PBILMethod(),
                        new PowellMethod(),
                        new PSOMethod(),
                        new QuasiNewtonMethod(),
                        new RandomMethod(),
                        new SADEMethod(),
                        new SteepestDescentMethod(),
                        geneticAlgorithmEvolutionaryMethod,
                        new DeterministicCrowdingEvolutionaryMethod(),
                        differentialEvolutionEvolutionaryMethod,
                        evolutionaryStrategiesEvolutionaryMethod,
                        new ParallelHillClimbingEvolutionaryMethod(),
                        new SequentialNichingEvolutionaryMethod(),
                        new PureCMAESMethod(),
                        new IPOPCMAESMethod(),
                        new SigmaMeanIPOPCMAESMethod()
                ), "methods");

        /**
         * OBJECTS IN THIS LIST HAVE TO BE THE SAME INSTANCES AS OBJECTS IN "methods".
         * THAT'S THE ONLY WAY WHICH THE STOP_CONDITIONS COULD BE CHANGED - FROM CONFIGURATION OF THE METHOD ITSELF.
         */
        configurationContext.register(EvolutionaryOptimizationMethod.class,
                Arrays.asList(
                        geneticAlgorithmEvolutionaryMethod,
                        differentialEvolutionEvolutionaryMethod,
                        evolutionaryStrategiesEvolutionaryMethod
                ), "evolutionaryMethods");

        configurationContext.register(FunctionEvaluator.class,
                Arrays.asList(
                        new SimpleFunctionEvaluator(),
                        new ParallelFunctionEvaluator()
                ), "functionEvaluators");

        configurationContext.register(IndividualFactory.class,
                Arrays.asList(
                        new SimpleIndividualFactory()
                ), "individualFactories");

        configurationContext.register(PopulationFactory.class,
                Arrays.asList(
                        new SimplePopulationFactory()
                ), "populationFactories");

        configurationContext.register(RepresentationFactory.class,
                Arrays.asList(
                        new SimplePhenotypeRepresentationFactory(),
                        new SimpleGenotypeRepresentationFactory(),
                        new BitSetGenotypeRepresentationFactory()
                ), "representationFactories");

        configurationContext.register(FitnessFunction.class,
                Arrays.asList(
                        new SimpleFitnessFunction(),
                        new SimpleSharingFitnessFunction()
                ), "fitnessFunctions");

        configurationContext.register(SuppressingFitnessFunction.class,
                Arrays.asList(
                        new SimpleSuppressingFitnessFunction()
                ), "suppressingFitnessFunctions");

        configurationContext.register(DeratingFunction.class,
                Arrays.asList(
                        new PowerLawDeratingFunction(),
                        new ExponentialDeratingFunction()
                ), "deratingFunctions");

        configurationContext.register(DistanceFunction.class,
                Arrays.asList(
                        new EuclideanDistanceFunction(),
                        new HammingDistanceFunction(),
                        new ManhattanDistanceFunction()
                ), "allDistanceFunctions");

        configurationContext.register(SharingFunction.class,
                Arrays.asList(
                        new SimpleSharingFunction()
                ), "sharingDistanceFunctions");

        configurationContext.register(DistanceFunction.class, new ArrayList<DistanceFunction>(), "distanceFunctions");

        configurationContext.register(ReproductionOperator.class,
                Arrays.asList(
                        new GenotypeMutationReproductionOperator(),
                        new GenotypeNPointCrossoverReproductionOperator(),
                        new GenotypeUniformCrossoverReproductionOperator(),
                        new PhenotypeBlendingCrossoverReproductionOperator(),
                        new PhenotypeGaussianMutationReproductionOperator(),
                        new PhenotypeAdaptiveGaussianMutationReproductionOperator(),
                        new PhenotypeNPointCrossoverReproductionOperator(),
                        new PhenotypeUniformCrossoverReproductionOperator(),
                        new PhenotypeDifferentialEvolutionReproductionOperator()
                ), "allReproductionOperators");

        configurationContext.register(ReproductionOperator.class, new ArrayList<ReproductionOperator>(), "reproductionOperators");

        // key to distinct reproduction operators with input/output arity of 1 - used for Evolutionary strategies
        configurationContext.register(ReproductionOperator.class,
                Arrays.asList(
                        new GenotypeMutationReproductionOperator(),
                        new PhenotypeGaussianMutationReproductionOperator(),
                        new PhenotypeAdaptiveGaussianMutationReproductionOperator()
                ), "allMutationReproductionOperators");

        configurationContext.register(ReproductionOperator.class, new ArrayList<ReproductionOperator>(), "mutationReproductionOperators");

        configurationContext.register(HillClimbingMutationOperator.class,
                Arrays.asList(
                        new GenotypeHillClimbingMutationOperator(),
                        new PhenotypeHillClimbingMutationOperator()
                ), "allHillClimbingMutationOperators");

        configurationContext.register(HillClimbingMutationOperator.class, new ArrayList<HillClimbingMutationOperator>(), "hillClimbingMutationOperators");

        configurationContext.register(SelectionOperator.class,
                Arrays.asList(
                        new BinaryTournamentSelectionOperator(),
                        new FitnessProportionateSelectionOperator(),
                        new PermutationSelectionOperator(),
                        new RankProportionateSelectionOperator(),
                        new StochasticUniversalSamplingSelectionOperator(),
                        new TruncationSelectionOperator(),
                        new UniformDeterministicSelectionOperator(),
                        new UniformStochasticSelectionOperator(),
                        new DeterministicCrowdingSurvivalSelectionOperator(),
                        new DifferentialEvolutionParentSelectionOperator(),
                        new DifferentialEvolutionSurvivalSelectionOperator()
                ), "selectionOperators");

        configurationContext.register(RandomGenerator.class,
                Arrays.asList(
                        new SimpleRandomGenerator()
                ), "randomGenerators");

        configurationContext.register(Solver.class, Arrays.asList(SolverFactory.getNewInstance(1000), SolverFactory.getNewInstance(1000, 1000000)), "solvers");
        configurationContext.register(TelemetryVisualization.class, new ArrayList<TelemetryVisualization>(), "visualizations");
        return configurationContext;
    }
}
