/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wgcotera
 */
public class Factorial {
   public static int factorial(int n) 
   {
       if (n == 0)
       {
           return 1;          
       }
       else
           return n * factorial(n-1);
   }   
   
   public static void main(String[] args)
   {
       int p = 5;
       System.out.format("%d \n", factorial(p));
   }
}
