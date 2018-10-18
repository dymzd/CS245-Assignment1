import java.util.Random;


public class AssignmentTest{

    protected int [] arr;
	protected String populationStrategy;   // How should the array be populated? 
	protected SortingFactory factory;      // This creates different sorting algorithms.
	protected String [] algorithms = { "mergesort", "hybridsort"}; // Algos to use.

	
    public AssignmentTest() {
		populationStrategy = "random";
		createNewArray(10000);
		factory = new SortingFactory(false);
	}
	
	
	/**
	 * Constructor which sets the size of the array
	 * @param arraySize
	 */
	public AssignmentTest(int arraySize) {
		populationStrategy = "increasing";
		createNewArray(arraySize);
		factory = new SortingFactory(false); // Do not use a default value for the algorithm name.
	}
	
	
	/**
	 * Constructor which sets array size AND uses one of two strategies for populating the array.
	 * @param arraySize
	 * @param populationStrategy
	 */
	public AssignmentTest(int arraySize, String populationStrategy) {
		this.populationStrategy = populationStrategy;
		createNewArray(arraySize);
		factory = new SortingFactory(false); // Do not use a default value for the algorithm name.
    }
    
    
	/**
	 * Sets the array and populates it according to the population strategy.
	 * @param size
	 */
	protected void createNewArray(int size) {
		arr = new int[size];
		populateArray();
	}
	
	
	/**
	 * Changes the size of the array.
	 * @param newSize
	 */
	public void changeArraySize(int newSize) {
		createNewArray(newSize);
	}
	
	
	/**
	 * Populates the array according to the variable "populationStrategy," defaulting to "random."
	 */
	protected void populateArray() {
		if (populationStrategy.contains("increasing")) {
			populateArrayIncreasing();
		}
		// else
		populateArrayRandomly();
	}
	
	
	/**
	 * Populates the array so that the values are sorted order (increasing).
	 */
	protected void populateArrayIncreasing() {
		Random r = new Random();
		int limit = (Integer.MAX_VALUE) / arr.length;
		arr[0] = Integer.MIN_VALUE + r.nextInt(limit);
		for (int i = 1; i < arr.length; i++) {
			arr[i] = arr[i-1] + r.nextInt(limit);
		}
	}
	
	
	/**
	 * Populates the array with random values.
	 */
	protected void populateArrayRandomly() {
		Random r = new Random();
		for (int i = 0; i < arr.length; i++) {
			// arr[i] = r.nextInt();
			arr[i] = r.nextInt(100);
		}
	}
	
	
	/**
	 * Checks whether the array is sorted.
	 * @return true if sorted; false otherwise.
	 */
	protected boolean isSorted(int [] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			if (arr[i] > arr[i+1])
				return false;
		}
		return true;
	}
	
	
	/**
	 * Prints the array size and whether sorted or not.
	 */
	public void printStatus() {
		System.out.print(arr.length + "\t");
		if (isSorted(arr))
			System.out.println("[OK]");
		else
			System.out.println("[XX] -- not sorted");
	}
	
	
	/**
	 * Prints the array size and whether sorted or not.
	 * @param The array to be considered.
	 */
	public void printStatus(int [] arr) {
		System.out.print(arr.length + "\t");
		if (isSorted(arr))
			System.out.println("[OK]");
		else
			System.out.println("[XX] -- not sorted");
	}
	
	
	/**
	 * Makes a copy of the array. This helps to compare sorting algorithms.
	 * @return a copy of the internal array.
	 */
	public int [] copyArray() {
		int [] copy = new int[arr.length];
		System.arraycopy(arr, 0, copy, 0, arr.length);
		return copy;
	}
	
    public void printSortingTiming() {
		// Objective:
		// 1) Feed "algorithms" variable to the factory in order to get a sorting algorithm:
		for (String algo : algorithms) {
			try {
				SortingAlgorithm sort = factory.getSortingAlgorithm(algo);
				// System.out.println("----------------------------------------------------");
				// System.out.println("algorithm: " + algo);
				System.out.print(algo + "\t");
				// For each algorithm:
				// a) Copy the array
				int [] copy = copyArray();
				// b) Have the algorithm sort the copy ... while timing it.
				long start = System.currentTimeMillis();
				sort.sort(copy);
				// System.out.println("Sorting took: " + (System.currentTimeMillis() - start) + " ms.");
				long total_time = System.currentTimeMillis() - start;
				System.out.print(total_time + " ms.\t");
				if (total_time < 10000) {
					System.out.print("\t");
				}
				// c) Check for correctness
				printStatus(copy);
				// System.out.println("----------------------------------------------------");
			}
			catch (Exception e) {
				System.out.println("Unable to instantiate sorting algorithm " + algo);
			}
		}
	}

	public static void main(String[] args) {
		
		AssignmentTest timing = new AssignmentTest();
		// int [] sizes = {1000,2000,3000,4000,5000,10000,12000,13000,15000};
		// int [] sizes = {5000};
		int [] sizes = {50000,100000,150000};


		// int [] sizes = {5000,10000,15000};
		// int [] test = {10,7,8,5,6,10,1,2,4};
		// for(int t: test){
		// timing.printSortingTiming();

		// }

		for (int size : sizes) {
			timing.changeArraySize(size);
			// timing.printStatus();
			timing.printSortingTiming();
			System.out.println("----------------------------------------------------");
		}
	}

}