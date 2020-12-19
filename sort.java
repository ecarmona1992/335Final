import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;


public class sort extends JPanel {

   private int[] insertionArray;

   //textarea for displaying array
   JTextArea sortedArray = new JTextArea();

   JTextArea preSort = new JTextArea();

   // variables for display
   private static final int arraySize = 20, BarW = 15, barSpace = 5,MAX = 80, MIN = 1, SS_Y_START = 120, yStart = 270;
   private JButton changeButton;
   //track count
   private int selectionTrack = 0, insertionTrack = 0;

   public sort() {
       insertionArray = new int[arraySize];

       //initialize random numbers 
       Random rn = new Random();
       int randomNumber;
       for (int i = 0; i < arraySize; i++) {
           randomNumber = MIN + rn.nextInt(MAX - MIN + 1);
           insertionArray[i] = randomNumber;
       }

       setBackground(Color.white);
       setPreferredSize(new Dimension(300, 300));

       //add button
       changeButton = new JButton("Click to sort array");
       add(changeButton);
       changeButton.addActionListener(new ButtonHandler());

       //initialize array
       sortedArray.setBounds(10, 10, 200, 60);
       sortedArray.setEditable(false);
       add(sortedArray);

       preSort.setBounds(150, 320, 200, 60);
       preSort.setEditable(false);
       for (int i =0; i <arraySize; i++){
        preSort.append(Integer.toString(insertionArray[i]));
        preSort.append(" ");
        add(preSort);
     }
       
   }

   public void paintComponent(Graphics pen) {
       super.paintComponent(pen);

       int xPos = 150;

       pen.setColor(Color.black);
       for (int index = 0; index < arraySize; index++) {
           pen.fillRect(xPos, yStart - insertionArray[index], BarW,insertionArray[index]);
           xPos = xPos + BarW + barSpace; // adds width and spaces                                    
       }

       xPos = 280;

       pen.setColor(Color.black);
       pen.drawString("Insertion Sort", xPos, yStart + 20);

   }

   private void modifiedInsertionSort() {
       // Insertion sort logic
       int j = insertionTrack;
       if (j < insertionArray.length) {
           int key = insertionArray[j];
           int i = j - 1;
           while ((i > -1) && (insertionArray[i] > key)) {
               insertionArray[i + 1] = insertionArray[i];
               i--;
           }
           insertionArray[i + 1] = key;
       }
   }

   public void printArr(){
       
       for (int i =0; i <insertionArray.length; i++){
          sortedArray.append(Integer.toString(insertionArray[i]));
          sortedArray.append(" ");
       }
   }

   private class ButtonHandler implements ActionListener {
       public void actionPerformed(ActionEvent e) {
          
           //Calling selectionSort and InsertionSort
           modifiedInsertionSort();
           selectionTrack++;
           insertionTrack++;
           try
        {
            Thread.sleep(200);
        }
            catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

           //Re-load the bar graphs
           sort.this.paintComponent(sort.this.getGraphics());

           // Check if end of array is reached. recursive call to loop through array
           if ((selectionTrack >= arraySize)&& (insertionTrack >= arraySize)) {
               preSort.setVisible(false);
                printArr();
               JOptionPane.showMessageDialog(null,
                       "The array is sorted!");
           }
           else
           actionPerformed(e);
       }
   }

   public static void main(String[] args) {
       sort panel = new sort();
       JFrame frame = new JFrame("Sort!");
       frame.getContentPane().add(panel, BorderLayout.CENTER);
       frame.setSize(700, 450);
       frame.setVisible(true);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
   }

}