package NeuralNet;
import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Matrix {

	//local variables
	public static int rows;
	public static int cols;
	public static float[][] matrix;

	//constructor
	public Matrix(int r, int c) {
		rows = r;
		cols = c;
		matrix = new float[rows][cols];
	}

	//constructor from 2D array
    Matrix(float[][] m) {
		matrix = m;
		cols = m.length;
		rows = m[0].length;
	}

	//print matrix


	//multiply by scalar
	public static void multiply(float n ) {

		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				matrix[i][j] *= n;
			}
		}
	}

	//return a matrix which is this matrix dot product parameter matrix
	public Matrix dot(Matrix n) {
		Matrix result = new Matrix(rows, n.cols);

		if (cols == n.rows) {
			//for each spot in the new matrix
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<n.cols; j++) {
					float sum = 0;
					for (int k = 0; k<cols; k++) {
						sum+= matrix[i][k]*n.matrix[k][j];
					}
					result.matrix[i][j] = sum;
				}
			}
		}

		return result;
	}

	//set the matrix to random ints between -1 and 1
	public static void randomize() {
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
                Random r = new Random();
                int Low = -1;
                int High = 1;
                matrix[i][j] = r.nextInt(High-Low) + Low;
			}
		}
	}

	//add a scalar to the matrix
	public static void Add(float n ) {
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				matrix[i][j] += n;
			}
		}
	}

	//return a matrix which is this matrix + parameter matrix to make OP matrix
	public Matrix add(Matrix n ) {
		Matrix newMatrix = new Matrix(rows, cols);
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] + n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}

	//return a matrix which is this matrix - parameter matrix
	public Matrix subtract(Matrix n ) {
		Matrix newMatrix = new Matrix(cols, rows);
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] - n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}

	//return a matrix which is this matrix * parameter matrix (element wise multiplication)
	public Matrix multiply(Matrix n ) {
		Matrix newMatrix = new Matrix(rows, cols);
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] * n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}

	//return a matrix which is the transpose of this matrix
	public Matrix transpose() {
		Matrix n = new Matrix(cols, rows);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[j][i] = matrix[i][j];
			}
		}
		return n;
	}

	//Creates a single column array from the parameter array to get specific columns or rows
	public Matrix singleColumnMatrixFromArray(float[] arr) {
		Matrix n = new Matrix(arr.length, 1);
		for (int i = 0; i< arr.length; i++) {
			n.matrix[i][0] = arr[i];
		}
		return n;
	}

	//sets this matrix from an array
	public static void fromArray(float[] arr) {
		for (int i = 0; i< rows; i++) {
			for (int j = 0; j< cols; j++) {
				matrix[i][j] =  arr[j+i*cols];
			}
		}
	}

	//returns an array which represents this matrix
	public float[] toArray() {
		float[] arr = new float[rows*cols];
		for (int i = 0; i< rows; i++) {
			for (int j = 0; j< cols; j++) {
				arr[j+i*cols] = matrix[i][j];
			}
		}
		return arr;
	}

	//for ix1 matrixes adds one to the bottom
	public Matrix addBias() {
		Matrix n = new Matrix(rows+1, 1);
		for (int i =0; i<rows; i++) {
			n.matrix[i][0] = matrix[i][0];
		}
		n.matrix[rows][0] = 1;
		return n;
	}

	//applies the activation function(sigmoid) to each element of the matrix
	public Matrix activate() {
		Matrix n = new Matrix(rows, cols);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[i][j] = sigmoid(matrix[i][j]);
			}
		}
		return n;
	}

	//sigmoid activation function that I found on wikipedia
	float sigmoid(float x) {
        float y = 1;
        float base = (float) Math.E;
        float power = -x;
        for( int i = 0; i < power; i++ ) {
            y = 1 / (base*base);
        }
		y = y + 1;
		return y;
	}
	//returns the matrix that is the derived sigmoid function of the current matrix that I also found on wikipedia
	public Matrix sigmoidDerived() {
		Matrix n = new Matrix(rows, cols);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[i][j] = (matrix[i][j] * (1- matrix[i][j]));
			}
		}
		return n;
	}

	//remove the button layer of the matrix
	Matrix removeBottomLayer() {
		Matrix n = new Matrix(rows-1, cols);
		for (int i =0; i<n.rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[i][j] = matrix[i][j];
			}
		}
		return n;
	}


	//Mutation function to teach halite to get better
	public static void mutate(float mutationRate) {

		//for each element in the matrix
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
                Random random = new Random();
                float rand = random.nextFloat();

				if (rand<mutationRate) {//if chosen to be mutated
				    Random r = new Random();
				    float randomG = (float) r.nextGaussian();
				    float randomG2 = (float) randomG;
					matrix[i][j] += randomG2/5;//add a random value to it(can be negative)

					//set the boundaries to 1 and -1
					if (matrix[i][j]>1) {
						matrix[i][j] = 1;
					}
					if (matrix[i][j] <-1) {
						matrix[i][j] = -1;
					}
				}
			}
		}
	}

//matrix with random values from parameter works with the genetic algorithm
	public Matrix crossover(Matrix partner) {
		Matrix child = new Matrix(rows, cols);

		//random point in matrix
        Random r = new Random();
        int low = 0;
        int highC = cols;
        int highR = rows;
        int randC = Math.round(r.nextInt(highC-low) + low);
        int randR = Math.round(r.nextInt(highR-low) + low);

		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {

				if ((i< randR)|| (i==randR && j<=randC)) { //if before the random point then copy from this matric
					child.matrix[i][j] = matrix[i][j];
				} else { //if after the random point then copy from the parameter array
					child.matrix[i][j] = partner.matrix[i][j];
				}
			}
		}
		return child;
	}

	//return a copy of this matrix
	public Matrix duplicate() {
		Matrix duplicate = new  Matrix(rows, cols);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				duplicate.matrix[i][j] = matrix[i][j];
			}
		}
		return duplicate;
	}
}
