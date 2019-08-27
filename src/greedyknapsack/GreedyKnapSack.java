package greedyknapsack;
/**
 *
 * @author NSTG
 */
import java.util.Scanner;

public class GreedyKnapSack {
    static Scanner scan = new Scanner(System.in);
    
    static double pr[] = new double[100];
    static double wt[] = new double[100];
    static double ratio[] = new double[100];
    static double seq[] = new double[100];
    
    public static void main(String[]args){
        int n;
        double m;
        
        System.out.println("Enter number of objects: ");
        n = scan.nextInt();
        
        System.out.println("Enter capacity: ");
        m = scan.nextInt();
        
        System.out.println("Enter profits: ");
        for(int i = 0; i < n; i++) {
            pr[i] = scan.nextInt();
	}
        System.out.println("Enter weights: ");
        for(int i = 0; i < n; i++) {
            wt[i] = scan.nextInt();
	}
        
        for (int i = 0; i < n; i++) {
            ratio[i] = pr[i] / wt[i];
        }
        for (int i = 0; i < n; i++){
            seq[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ratio[i] < ratio[j]) {
                double temp = ratio[j];
                ratio[j] = ratio[i];
                ratio[i] = temp;
 
                temp = wt[j];
                wt[j] = wt[i];
                wt[i] = temp;
 
                temp = pr[j];
                pr[j] = pr[i];
                pr[i] = temp;
                
                temp = seq[j];
                seq[j] = seq[i];
                seq[i] = temp;
                }
            }
        }
        /*System.out.print("\nX = {");
        for(int i = 0; i < n; i++) {
            System.out.print(seq[i]+", ");
        }*/
        knapsack(n, wt, pr, m);
        
    }
    public static void knapsack(int n, double wt[], double pr[], double m){
        double X[] = new double[100];
        double total_profit = 0;
        double U = m;
        int i;
        
        for (i = 0; i < n; i++){
            X[i] = 0;
        }
        for (i = 0; i < n; i++) {
            if (wt[i] > U){
                break;
            }
            else {
                X[i] = 1;
                total_profit = total_profit + pr[i];
                U = U - wt[i];
            }
        }
        
        if (i < n){
            X[i] = U / wt[i];
        }
        
        total_profit = total_profit + (X[i] * pr[i]);
        
        for (i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (seq[i] > seq[j]) {
                double temp = X[i];
                X[i] = X[j];
                X[j] = temp;
                             
                temp = seq[i];
                seq[i] = seq[j];
                seq[j] = temp;
                }
            }
        }
        
        
        System.out.print("\nX = {");
        for(i = 0; i < n; i++) {
            System.out.print(X[i]+", ");
        }
        System.out.print("}");
        
        System.out.print("\n\nOptimal Profit: "+total_profit+"\n");
    }
