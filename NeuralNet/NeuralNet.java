package NeuralNet;
import NeuralNet.*;
import java.lang.System.*;
import java.io.*;
import java.lang.System.*;
import java.lang.Float.*;
import java.*;
import java.util.*;

public class NeuralNet {

    int iNodes; //input nodes
    int hNodes; //hidden nodes
    int oNodes; //output nodes

    Matrix whi;//matrix with weights between input and hidden layer #1
    Matrix whh;//matrix with weights between the hidden nodes #1 and the hidden layer nodes #2
    Matrix woh;//matrix with weights between the hidden layer #2 nodes and the output nodes

    //first constructor call in MyBot.java
    public NeuralNet(int inputs, int hiddenNo, int outputNo) {

        //set dimensions
        iNodes = inputs;
        oNodes = outputNo;
        hNodes = hiddenNo;


        //create first layer weights
        //included bias weight
        whi = new Matrix(hNodes, iNodes +1);

        //create second layer weights
        //include bias weight
        whh = new Matrix(hNodes, hNodes +1);

        //create second layer weights
        //include bias weight
        woh = new Matrix(oNodes, hNodes +1);

        //set the matricies to random values to begin evolution
        whi.randomize();
        whh.randomize();
        woh.randomize();
    }

    //mutation function for genetic evolution
    public void mutate(float mr) {
        //mutates each WEIGHT matrix
        whi.mutate(mr);
        whh.mutate(mr);
        woh.mutate(mr);
    }

    //calculate the output values by going through the neural network
    float[] output(float[] inputsArr) {

        //convert array to matrix
        //Note woh has nothing to do with it its just a function in the Matrix class
        Matrix inputs = woh.singleColumnMatrixFromArray(inputsArr);

        //add bias
        Matrix inputsBias = inputs.addBias();


        //apply layer one weights to the inputs
        Matrix hiddenInputs = whi.dot(inputsBias);

        //pass through activation function(sigmoid) that I found on wikipedia
        Matrix hiddenOutputs = hiddenInputs.activate();

        //add bias
        Matrix hiddenOutputsBias = hiddenOutputs.addBias();

        //apply layer two weights
        Matrix hiddenInputs2 = whh.dot(hiddenOutputsBias);
        Matrix hiddenOutputs2 = hiddenInputs2.activate();
        Matrix hiddenOutputsBias2 = hiddenOutputs2.addBias();

        //apply level three weights
        Matrix outputInputs = woh.dot(hiddenOutputsBias2);
        //pass through activation function(sigmoid)
        Matrix outputs = outputInputs.activate();

        //convert to an array and return
        return outputs.toArray();
    }

    //offspring algorithm
    NeuralNet crossover(NeuralNet partner) {

        //creates a new child with layer matrices from both parents
        NeuralNet child = new NeuralNet(iNodes, hNodes, oNodes);
        child.whi = whi.crossover(partner.whi);
        child.whh = whh.crossover(partner.whh);
        child.woh = woh.crossover(partner.woh);
        return child;
    }

    //return a neural net which is a clone of this Neural net
    public NeuralNet clone() {
        NeuralNet clone  = new NeuralNet(iNodes, hNodes, oNodes);
        clone.whi = whi.duplicate();
        clone.whh = whh.duplicate();
        clone.woh = woh.duplicate();

        return clone;
    }

    //converts the weights matrices to 3 arrays
    //used for storing the brain in a file
    public File NetToTable() {

        //convert the matricies to an array
        float[] whiArr = whi.toArray();
        float[] whhArr = whh.toArray();
        float[] wohArr = woh.toArray();
        PrintWriter out = null;
        File file = new File("Brain.txt");
        file.getParentFile().mkdirs();
        try {
            out = new PrintWriter(file);

            for (int i = 0; i < whiArr.length; i++) {
                out.print(":" + String.valueOf(whiArr[i]));
            }

            out.println("");

            for (int i = 0; i < whhArr.length; i++) {
                out.print(":" + String.valueOf(whhArr[i]));
            }

            out.println("");

            for (int i = 0; i < wohArr.length; i++) {
                out.print(":" + String.valueOf(wohArr[i]));
            }

            //:0, 4:i, x:i, x:i, x
            out.close();

        } catch (FileNotFoundException ex){
            //L
        }
        return file;
    }


    //takes in .txt document as parameter and overwrites the matrices data for this neural network
    //used to load good neuralnet from file
    public void TableToNet(File in) {

        //create arrays to temporarily store the data for each matrix
        String[] whiArrString = new String[whi.rows * whi.cols];
        String[] whhArrString = new String[whh.rows * whh.cols];
        String[] wohArrString = new String[woh.rows * woh.cols];
        float[] whiArr = new float[whi.rows * whi.cols];
        float[] whhArr = new float[whh.rows * whh.cols];
        float[] wohArr = new float[woh.rows * woh.cols];

        try {
            Scanner scanner = new Scanner(in);
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                whiArrString = line.split(":");
                for(int i=0; i<whiArrString.length; i++){
                    float whival = Float.parseFloat(whiArrString[i]);
                    whiArr[i] = whival;
                }

                line = scanner.nextLine();
                whhArrString = line.split(":");
                for(int i=0; i<whhArrString.length; i++){
                    float whhval = Float.parseFloat(whhArrString[i]);
                    whhArr[i] = whhval;
                }

                line = scanner.nextLine();
                wohArrString = line.split(":");
                for(int i=0; i<wohArrString.length; i++){
                    float wohval = Float.parseFloat(wohArrString[i]);
                    wohArr[i] = wohval;
                }
            }

        } catch (Exception e){
            //L
        }

        //convert the arrays to matrices and set them as the layer matrices
        whi.fromArray(whiArr);
        whh.fromArray(whhArr);
        woh.fromArray(wohArr);
    }
}
