//Jamil Khan
//CECS 328 Lab 6
//4-9-19
//Pouye Sedighian

package heapify;

import java.util.Arrays;
import java.util.Scanner;

public class MaxHeapify_Sort {
	private static int large = 20001;
	private static int small = -10000;
	private static int loops = 100;
	private static int bInt = 10;
	
	public static void main(String[] args) {
		
		System.out.println("Part A of Programming Assignment: ");
		int i, j, n, item;
		int[] a;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a positive integer: ");
		while(!scan.hasNextInt() || (n = scan.nextInt()) <= 0) {
			System.out.println("ERROR! Enter a positive integer: ");
			scan.nextLine();
		}
		a = new int[n];
		//the average running time of Heap Sort algorithm will be calculated
		//an array of random numbers between -10000 and +10000 will be created
		//with size defined by user, and then the array will be sorted
		//array elements will be placed on a binary heap and max_heapified and then sorted
		//these two steps will be repeated 100 times
		//the total time will then be divided by 100 to calculate average time
		long totalTime = 0;
		long startTime;
		long runTime;
		//loops equals 100
		for(i = 0; i < loops; i++) {
			// for loop used to fill in array a with integer elements
			// elements entered into array will be between -10000 and +10000
			// loop terminates once the whole array is filled with elements
			for(j = 0; j < n; j++) {
				item = (int)((Math.random() * large)+small);
				while(Arrays.asList(a).contains(item)) {
					item = (int)((Math.random() * large)+small);
				}
				a[j] = item;
			}
			startTime = System.nanoTime();
			heap_sort(a);
			runTime = System.nanoTime() - startTime;
			totalTime += runTime;
		}
		//calculates average time in nanoseconds
		float avgTime = totalTime / loops;
		//convert nanoseconds to seconds
		float avgSec = (float) (avgTime*Math.pow(10, -9));
		System.out.println("Average running time for Heap Sort algorithm of array size " 
					+ n + " and with 100 repititions is " + avgTime + " nanoseconds.");
		System.out.println("This is equivalent to " + avgSec + " seconds.");
		
		float firstTime = avgTime;
		//populate array a with new elements and calculate average running time
		//of selection sort with 1000 elements and 100 repetitions;
		
		totalTime = 0;
		for(i = 0; i < loops; i++) {
			for(j = 0; j < n; j++) {
				item = (int)((Math.random() * large)+small);
				while(Arrays.asList(a).contains(item)) {
					item = (int)((Math.random() * large)+small);
				}
				a[j] = item;
			}
			startTime = System.nanoTime();
			selection_sort(a);
			runTime = System.nanoTime() - startTime;
			totalTime += runTime;
		}
		//calculates average time in nanoseconds
		float averageTime = totalTime / loops;
		//convert nanoseconds to seconds
		float averageSeconds = (float) (averageTime*Math.pow(10, -9));
		System.out.println("Average running time for Selection Sort algorithm of array size " 
					+ n + " and with 100 repititions is " + averageTime + " nanoseconds.");
		System.out.println("This is equivalent to " + averageSeconds + " seconds.");
		
		float secondTime = averageTime;
		if(firstTime < secondTime) {
			System.out.println("Average run of Heap Sort ran faster than average run of Selection Sort this time.");
		}
		if(secondTime < firstTime) {
			System.out.println("Average run of Selection Sort ran faster than average run of Heap Sort this time.");
		}
		
		System.out.println("\nPart B of Programming Assignment: ");
		a = new int[bInt];
		for(j = 0; j < bInt; j++) {
			item = (int)((Math.random() * large)+small);
			while(Arrays.asList(a).contains(item)) {
				item = (int)((Math.random() * large)+small);
			}
			a[j] = item;
		}
		System.out.println("Generated array size 10: " + Arrays.toString(a));
		heap_sort(a);
		System.out.println("Sorted array: " + Arrays.toString(a));
	}

	/**
	 * sorts array by placing smallest element found in array at beginning
	 * @param array - array to be sorted using selection sort
	 */
	public static void selection_sort(int[] array) {
		//size of array
		int length = array.length;
		//index of min element in array
		int min;
		for(int i = 0; i < length-1; i++) {
			//sets element at beginnning as min
			min = i;
			//checks all elements after beginning
			for(int j = i+1; j < length; j++) {
				//sets min to jth element
				//if jth element is smaller
				//than current min element
				if(array[j] < array[min]) {
					min = j;
				}	
			}
			//swaps min element found
			//with ith element at beginning
			int Temp = array[i];
			array[i] = array[min];
			array[min] = Temp;
		}
		
		
	}
	
	public static void heap_sort(int[] array) {
		int size = array.length;
		//builds max heap from array
		array = build_MaxHeap(array);
		//swaps last element with root
		//after swapping, max heapifying
		//occurs, in which new max element
		//is placed at top of heap, and then
		//is swapped with root, at which point
		//recursion occurs to finish sorting
		for(int i = size-1; i>= 0; i--) {
			int Temp = array[i];
			array[i] = array[0];
			array[0] = Temp;
			//call max heapify to finish sort
			max_heapify(array, i, 0);
		}
	}
	
	/**
	 * builds max heap from an array of integers; calls max_heapify
	 * to organize the elements of the array based on value
	 * @param - array of integers to be manipulated and organized
	 * @return - an organized array of integers which can be
	 * 			 placed onto a binary tree as a max heap
	 */
	public static int[] build_MaxHeap(int[] array) {
		int length = array.length;
		//loop starts from last internal node and uses
		//max_heapify function until reaching top root
		//of binary tree/heap
		for(int i = length/2 - 1; i >= 0; i--) {
			max_heapify(array, length, i);
		}
		return array;
	}
	
	/**
	 * recursive function which swaps root and largest element, if they are not already
	 * the same, within a tree; recursive calls continue starting from position of where
	 * largest element used to be, or position of where root was placed after calculation 
	 * @param array - array of integers within which swapping may occur
	 * @param length - size/length of array/heap
	 * @param index - index of root of subtree
	 */
	public static void max_heapify(int[] array, int length, int index) {
		//index of root of subtree
		int biggest = index;
		//index of left child
		int left = (2*index) + 1;
		//index of right child
		int right = (2*index) + 2;
		if((left < length) && (array[left] > array[biggest])) {
			biggest = left;
		}
		if((right < length) && (array[right] > array[biggest])) {
			biggest = right;
		}
		//swaps largest element found and root element 
		//if they are not the same element
		if(biggest!=index) {
			int Temp = array[index];
			array[index] = array[biggest];
			array[biggest] = Temp;
			//recursive call to continue max-heapifying
			max_heapify(array, length, biggest);
			
		}
	}

}
