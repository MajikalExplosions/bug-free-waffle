import java.utils.*;

class Matrix {

//random vars
	int rows;
	int columns;
	float[][] matrix;

//default matrix
	Matrix(int r, int c) {
    rows = r;
    columns = c;
    matrix = new float[rows][cols];
  }

//2D array where we are able to build layers ontop of
  Matrix(float[][] m) {
    matrix = m;
    columns = m.length;
    rows = m[0].length;
  }

//test matrix by printing only way that I could think of doing it lolllll
void output() {
	for (int i =0; i<rows; i++) {
    	for (int j = 0; j<columns; j++) {
        	print(matrix[i][j] + "  ");
      	}
      	println(" ");
    }
    println();
}

//make neural net stronger
void stronger(float n) {
    for (int i =0; i<rows; i++) {
      	for (int j = 0; j<cols; j++) {
        	matrix[i][j] *= n;
      	}
    }
}

Matrix dot(Matrix n) {
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

}
