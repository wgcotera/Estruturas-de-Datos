/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wgcotera
 */
public class Fibonacci 
{
    
    public static int fibonacci(int n) 
    {
        switch (n) 
        {
            case 1: { return 0; }
            case 2: { return 1; }
            default: { return fibonacci(n-1) + fibonacci(n-2); }            
        }
    }
    
    public static void main(String[] args) 
    {
        for (int i = 1 ; i <= 14 ; i++) 
        {
            System.out.format("fibonnaci de %d is %d \n", i, fibonacci(10));            
        }
    }
}
