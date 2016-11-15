/*
Name: Diaz, Edmar
USN: 15000696700
Course/Section: BSCS/QS2A 
*/
import java.io.*;
import java.lang.Math;
import java.util.*;

class midterm
{ 
    // global variables
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // given equation container
    static double fx;
    // delta x
    static double delta_x;
    // limits of integral
    static float a;
    static float b;
    // store value of x in estimate table
    static ArrayList<Double> values_x = new ArrayList<Double>();
    // store value of y in estimate table
    static ArrayList<Double> values_y = new ArrayList<Double>();
    // print gui-menu
    public static void printMenu()
    {
        System.out.println("[1] Trapezoidal Rule");
        System.out.println("[2] Simpson's 1/3 Rule");
        System.out.println("[3] Simpson's 3/8 Rule'");
        setChoice();
        // check if choice exist in menu
        while(choice <= 0 || choice >=4)
        {
            System.out.println("Your choice does not exist!");
            setChoice();
        }
        // redirect to specific choice
        checkChoice(choice);
    }
    // ask user for choice
    static int choice;
    public static void setChoice()
    {
        System.out.print("Choose: ");
        // check if user input is character or number
        try
        {
            choice = Integer.parseInt(br.readLine());
        }
        catch(Exception e)
        {
            System.out.println("Your choice must be a corresponding to the menu!");
            System.out.print("Choose: ");
            setChoice();
        }
    }
    // check choice 
    public static void checkChoice(int c)
    {
        if(choice == 1)
        {
            trapezoidalMethod();
        }
        else if(choice == 2)    
        {
            simpsons_1_3_rule();
        }
        else if(choice == 3)
        {
            simpsons_3_8_rule();
        }
    }
    // trapezoidalMethod
    public static void trapezoidalMethod()
    {
        // ask for a and b
        setLimit();
        //ask for n
        try{
            setN();
        }catch(Exception e){ e.printStackTrace(); }
        // compute for delta x
         delta_x = computeDeltaX(a, b, n);
        // make x = a
        double x=a;
        // <------- fx
         fx =(Math.sqrt(x)*(1-x));
        // iterate to compute estimate tables nd save them
        for(x = a; x<=b; x+=delta_x)
        {
            values_x.add(x);
            fx =(Math.sqrt(x)*(1-x));
            values_y.add(fx);
        }
        // print estimate table xn and yn
        System.out.println("Estimate table: ");
        for(int z = 0;z<values_x.size();z++)
        {
            System.out.println("X"+z+": \t\t"+"Y"+z+":");
            System.out.printf("%.6f"+"\t"+"%.6f\n",values_x.get(z),values_y.get(z));
            System.out.println("---------------------------");
        }
        // compute for approximate integral
        float sum=0;
		for(int z=0;z<values_y.size();z++)
		{
			// check for y0 and yn
			if(z==0 || z == values_y.size()-1)
			{
				sum += values_y.get(z);
			}
			else
			{
				sum += 2*values_y.get(z);
			}
		}
		double final_ans = (delta_x/2)*sum;
		System.out.printf("Approximate Integral: %.6f",final_ans);
    }
    // simpsons 1/3 rule
    public static void simpsons_1_3_rule()
    {
        // ask for a and b
        setLimit();
        //ask for n
        try{
            setN();
        }catch(Exception e){ e.printStackTrace(); }
        //check if n is even 
        while(isEven(n) == false)
        {
            System.out.println("You n must be even number!");
            setN();
        }
        // compute for delta x
         delta_x = computeDeltaX(a, b, n);
        // make x = a
        double x=a;
        // <------- fx
         fx =(Math.sqrt(x)*(1-x));
        // iterate to compute estimate tables nd save them
        for(x = a; x<=b; x+=delta_x)
        {
            values_x.add(x);
            fx =(Math.sqrt(x)*(1-x));
            values_y.add(fx);
        }
        // print estimate table xn and yn
        System.out.println("Estimate table: ");
        for(int z = 0;z<values_x.size();z++)
        {
            System.out.println("X"+z+": \t\t"+"Y"+z+":");
            System.out.printf("%.6f"+"\t"+"%.6f\n",values_x.get(z),values_y.get(z));
            System.out.println("---------------------------");
        }
        // compute for approximate integral
        float sum=0;
		for(int z=0;z<values_y.size();z++)
		{
			// check for y0 and yn
			if(z==0 || z == values_y.size()-1)
			{
				sum += values_y.get(z);
			}
			else
			{
                if(isEven(z) == true)
                {
				    sum += 2*values_y.get(z);
                }
                else
                {
                    sum += 4*values_y.get(z);
                }
			}
		}
		double final_ans = (delta_x/3)*sum;
		System.out.printf("Approximate Integral: %.6f",final_ans);
    }
    // simpsons 3/8 Rule
    public static void simpsons_3_8_rule()
    {
        // ask for a and b
        setLimit();
        //ask for n
        try{
            setN();
        }catch(Exception e){ e.printStackTrace(); }
        //check if n is even 
        while(isDivBy3(n) == false)
        {
            System.out.println("Error: n not divisible by 3. Simpson's Rule can't be applied");
            setN();
        }
        // compute for delta x
         delta_x = computeDeltaX(a, b, n);
        // make x = a
        double x=a;
        // <------- fx
         fx =(Math.sqrt(x)*(1-x));
        // iterate to compute estimate tables nd save them
        while(x<=b)
        {
            values_x.add(x);
            fx =(Math.sqrt(x)*(1-x));
            values_y.add(fx);
            x=Math.floor((x+delta_x)*10000000.0)/10000000.0;
        }
        // print estimate table xn and yn
        System.out.println("Estimate table: ");
        for(int z = 0;z<values_x.size();z++)
        {
            System.out.println("X"+z+": \t\t"+"Y"+z+":");
            System.out.printf("%.6f"+"\t"+"%.6f\n",values_x.get(z),values_y.get(z));
            System.out.println("---------------------------");
        }
        // compute for approximate integral
        float sum=0;
		for(int z=0;z<values_y.size();z++)
		{
			// check for y0 and yn
			if(z==0 || z == values_y.size()-1)
			{
				sum += values_y.get(z);
			}
			else
			{
                if(isDivBy3(z) == true)
                {
				    sum += 2*values_y.get(z);
                }
                else
                {
                    sum += 3*values_y.get(z);
                }
			}
		}
		double final_ans = (3*delta_x/8)*sum;
		System.out.printf("Approximate Integral: %.6f",final_ans);
    }
    // ask for a and b (limits)
    public static void setLimit()
    {
        // low limit of an integral (a)
        System.out.print("LOWER Limit: ");
        try
        {
            a = Integer.parseInt(br.readLine());
        }
        catch(Exception e)
        {
            System.out.println("Your n must be a number/integer!");
            setLimit();
        }
        // higher limit of an integral (b)
        System.out.print("UPPER Limit: ");
        try
        {
            b = Float.parseFloat(br.readLine());
        }
        catch(Exception e)
        {
            System.out.println("Your n must be a number/integer!");
            setLimit();
        }
    }

    // compute delta x
    public static float computeDeltaX(float a, float b, float n)
    {
        return (b-a)/n;
    }
    // setN 
    static float n;
    public static void setN()
    {
        System.out.print("Value for n (4 to 10): ");
        // check if n is integer
        try
        {
            n = Integer.parseInt(br.readLine());
			checkN(n);
        }
        catch(Exception e)
        {
            System.out.println("Your n must be a number/integer!");
            setN();
        }
    }
	//check
	public static void checkN(float n)
	{
		if(n<=3 || n>10)
		{
			System.out.println("Your n must be 4 to 10!");
			setN();
		}
	}
    // return true if n is even
    public static boolean isEven(float n)
    {
        return (n%2 == 0) ? true : false; 
    }
    // overloaded method of isEven
    public static boolean isEven(Double x)
    {
        return (x%2 == 0) ? true : false;
    }
    // return true if n is div by 3
    public static boolean isDivBy3(float n)
    {
        return (n%3 == 0) ? true : false;
    }


    public static void main(String[] args)
    {
        printMenu();
    }
}

