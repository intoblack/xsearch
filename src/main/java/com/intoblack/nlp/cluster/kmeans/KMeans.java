package com.intoblack.nlp.cluster.kmeans;

import java.util.HashSet;
import java.util.Set;

public class KMeans {

	public int[] cluster(double[][] features, int k, int iter) {
		int lable[] = new int[features.length];
		for (int i = 0; i < lable.length; i++) {
			lable[i] = -1;
		}
		double centre[][] = initCentre(features, k);
		for (int iterCount = 0; iterCount < iter; iterCount++) {
			for (int i = 0; i < features.length; i++) {
				double d = 0;
				int l = 0;
				for (int j = 0; j < centre.length; j++) {
					double featureDistance = distance(features[i], centre[j]);
					if (featureDistance > d) {
						d = featureDistance;
						l = j;
					}

				}
				lable[i] = l;
			}
			updateCentre(features, centre, k, lable);
		}
		return lable;
	}

	public void updateCentre(double features[][], double centre[][], int k,
			int label[]) {
		int lableCount[] = new int[k];
		for (int i = 0; i < centre.length; i++) {
			for (int j = 0; j < centre[i].length; j++) {
				centre[i][j] = 0;
			}
		}
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < features[i].length; j++) {
				centre[label[i]][j] += features[i][j];
			}
			lableCount[label[i]] += 1;
		}
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < centre[i].length; j++) {
				centre[i][j] /= lableCount[i];
			}
		}
	}

	public double distance(double feature1[], double feature2[]) {
		double d = 0;
		for (int i = 0; i < feature1.length; i++) {
			d += (feature1[i] - feature2[i]) * (feature1[i] - feature2[i]);
		}
		return Math.sqrt(d);
	}

	public double[][] initCentre(double[][] features, int k) {
		Set<Integer> lableIndex = new HashSet<Integer>();
		double centre[][] = new double[k][features[0].length];
		for (int i = 0; i < k; i++) {
			int index = random(0, features.length - 1);
			if (!lableIndex.contains(index)) {
				centre[i] = features[index].clone();
				lableIndex.add(i);
				continue;
			}
			i--;

		}
		return centre;

	}

	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	public static void print(double[][] feature) {
		for (int i = 0; i < feature.length; i++) {
			for (int j = 0; j < feature[0].length; j++) {
				System.out.print(feature[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String args[]) {
		Kmeans km = new Kmeans();
		double features[][] = new double[1000][7];
		for (int i = 0; i < features.length; i++) {
			for (int j = 0; j < features[i].length; j++) {
				features[i][j] = Kmeans.random(0, 200);
			}
		}
		int lable[] = km.cluster(features, 10, 3000);
		for (int i = 0; i < lable.length; i++) {
			System.out.print(lable[i] + "\t");
			for (int j = 0; j < features[i].length; j++) {
				System.out.print(" " + features[i][j]);
			}
			System.out.println();
		}
	}

}
