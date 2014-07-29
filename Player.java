import java.util.*;
import java.lang.System.*;

/*
 * Method summary:
 * public void printCorrespond()
 * public int getCost(int[][] cost, int other, int strat)
 * public void printPlayer()
 * public void setFirstStrategy(int x)
 * public int getFirstStrategy()
 * public void setPreStrategy(int x)
 * public int getPreStrategy()
 * public void setAvgCost(int x)
 * public int getAvgCost()
 * public void setLastCost(int x)
 * public int getLastCost()
 */
public class Player
{
  private int num;

  private int[][] strategy0 = new int[4][8];
  private int[][] strategy1 = new int[4][8];
  private int[] correspond0 = new int[8];
  private int[] correspond1 = new int[8];

  private int preStrategy;
  private int firstStrategy;

  private int avgCost;
  private int lastCost;

  public Player(int[][] strategy, int num)
  {
     this.num = num;

     int n = 0;
     int m = 0;
     for(int j = 0; j < 16; j++){
       if(strategy[num][j] == 0){
	 for(int k = 0; k < 4; k++){
	   strategy0[k][n] = strategy[k][j];
           correspond0[n] = j;
	 }
	 n++;
       }else{
	 for(int k = 0; k < 4; k++){
	   strategy1[k][m] = strategy[k][j];
	   correspond1[m] = j;
	 }
	 m++;
       }
     }
     
  }

  public void printCorrespond()
  {
    System.out.print("Player " + num + " 0: ");
    for(int i = 0; i < 8; i++)
    {
      System.out.print(correspond0[i] + " ");
    }
    System.out.println("");

    System.out.print("Player " + num + " 1: ");
    for(int i = 0; i < 8; i++)
    {
      System.out.print(correspond1[i] + " ");
    }
    System.out.println("");
    System.out.println("");
  }

  public int getCorrespond(int me, int other)
  {
    if(me == 0){
      return correspond0[other];
    }else{
      return correspond1[other];
    }
  }

  public int getCost(int[][] cost, int other, int strat)
  {
    if(strat == 0){
      int i = correspond0[other];
      return cost[num][i];
    }else{
      int i = correspond1[other];
      return cost[num][i];
    }
  }

  public void printPlayer()
  {
    for(int x = 0; x < 8; x++){
      System.out.print("Player" + num + " Strategy0 " + x + " : (");
      for(int y = 0; y < 4; y++){
	System.out.print(strategy0[y][x] + " ");
      }
      System.out.println(")");
    }

    System.out.println("");

    for(int x = 0; x < 8; x++){
      System.out.print("Player" + num + " Strategy1 " + x + " : (");
      for(int y = 0; y < 4; y++){
	System.out.print(strategy1[y][x] + " ");
      }
      System.out.println(")");
    }

    System.out.println("");
  }

  public void setFirstStrategy(int x)
  {
    firstStrategy = x;
  }

  public int getFirstStrategy()
  {
    return firstStrategy;
  }

  public void setPreStrategy(int x)
  {
    preStrategy = x;
  }

  public int getPreStrategy()
  {
    return preStrategy;
  }

  public void setAvgCost(int x)
  {
    avgCost = x;
  }

  public int getAvgCost()
  {
    return avgCost;
  }

  public void setLastCost(int x)
  {
    lastCost = x;
  }

  public int getLastCost();
  {
    return lastCost;
  }
}
