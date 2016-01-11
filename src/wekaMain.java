import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.pmml.FieldMetaInfo.Value;
import writeAndRead.WriteLine;

public class wekaMain {

	public static void main(String[] args) throws Exception {
		Classifier classifier1;
		String trainFile = "Shixiongtrain_data_nopre_80w_256_.arff";
		String testFile = "Shixiongtest_data_nopre_20w_256_.arff";
		System.out.println("Loading data！");
		File inpFile = new File(trainFile);
		ArffLoader arf = new ArffLoader();
		arf.setFile(inpFile);
		Instances instancesTrain = arf.getDataSet();
		System.out.println("Loading train data.");
		instancesTrain.setClassIndex(instancesTrain.numAttributes() - 1);

		int k = Integer.parseInt(args[0]);
		if (k == 0) {
			System.out.println("start classifier.");
			classifier1 = (Classifier) Class.forName(
					"weka.classifiers.trees.RandomForest").newInstance();
			classifier1.buildClassifier(instancesTrain);
			System.out.println("RandomForest Model is ok!");
		} else {
			System.out.println("start classifier.");
			classifier1 = (Classifier) Class.forName(
					"weka.classifiers.functions.LibLINEAR").newInstance();
			classifier1.buildClassifier(instancesTrain);
			System.out.println("RandomForest Model is ok!");
		}

		System.out.println("Load test data.");
		inpFile = new File(testFile);
		arf.setFile(inpFile);
		Instances instancesTest = arf.getDataSet();
		instancesTest.setClassIndex(instancesTest.numAttributes() - 1);
		int sum = instancesTest.numInstances();
		System.out.println("Load test data over.");

		System.out.println("Result is writing!");
		WriteLine writer = new WriteLine("classShixionglibnear256.utf8");
		for (int i = 0; i < sum; i++) {
			int type = (int) classifier1.classifyInstance(instancesTest
					.instance(i));
			writer.appendLine(type);
		}
		System.out.println("Result writing is over!");
		writer.closeWrite();
		
		// 交叉验证
		System.out.println("开始交叉验证2");
		Evaluation eva1 = new Evaluation(instancesTrain);
		eva1.crossValidateModel(classifier1, instancesTrain, 3, new Random(1));
		System.out.println(eva1.errorRate());
		System.out.println(eva1.recall(1));
		System.out.println(eva1.recall(0));
		System.out.println("交叉验证完毕2！");

	}
}
