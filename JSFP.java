import java.lang.System.*;
import java.util.*;

public class JSFP
{
  private static int[][] strategy = new int[4][16];
  private static int[][] cost = new int[4][16];

  private Random random = new Random();
  private static int LIMIT = 10;

  public void setStrategy()
  {
    //strategy set for player 0
    int j = 0;
    int i = 15;
    while(i >= 0){
      strategy[0][15-i] = j;
      j = (j+1) % 2;
      i--;
    }

    //strategy set for player 1
    j = 0;
    i = 15;
    while(i >= 0){
      for(int k = 0; k < 2; k++){
        strategy[1][15-i] = j;
	i--;
      }
      j = (j+1) % 2;
    }

    //strategy set for player 2
    j = 0;
    i = 15;
    while(i >= 0){
      for(int k = 0; k < 4; k++){
        strategy[2][15-i] = j;
	i--;
      }
      j = (j+1) % 2;
    }

    //strategy set for player 3
    j = 0;
    i = 15;
    while(i >= 0){
      for(int k = 0; k < 8; k++){
        strategy[3][15-i] = j;
	i--;
      }
      j = (j+1) % 2;
    }
  }

  public void printStrategy()
  {
    for(int j = 0; j < 16; j++){
      System.out.print("Stratey " + j + " (");
      for(int i = 0; i < 4; i++){
        System.out.print(strategy[i][j] + " ");
      }
      System.out.println(")");
    }
  }

  public void setCost()
  {
    //cost for player 0
    cost[0][0] = 1;
    cost[0][1] = 4;
    cost[0][2] = 2;
    cost[0][3] = 3;
    cost[0][4] = 1;
    cost[0][5] = 4;
    cost[0][6] = 2;
    cost[0][7] = 4;
    cost[0][8] = 2;
    cost[0][9] = 3;
    cost[0][10] = 3;
    cost[0][11] = 2;
    cost[0][12] = 2;
    cost[0][13] = 4;
    cost[0][14] = 3;
    cost[0][15] = 3;

    //cost for player 1
    cost[1][0] = 1;
    cost[1][1] = 2;
    cost[1][2] = 4;
    cost[1][3] = 3;
    cost[1][4] = 2;
    cost[1][5] = 3;
    cost[1][6] = 3;
    cost[1][7] = 2;
    cost[1][8] = 1;
    cost[1][9] = 2;
    cost[1][10] = 5;
    cost[1][11] = 4;
    cost[1][12] = 2;
    cost[1][13] = 3;
    cost[1][14] = 4;
    cost[1][15] = 3;

    //cost for player 2
    cost[2][0] = 1;
    cost[2][1] = 1;
    cost[2][2] = 2;
    cost[2][3] = 2;
    cost[2][4] = 2;
    cost[2][5] = 3;
    cost[2][6] = 1;
    cost[2][7] = 2;
    cost[2][8] = 1;
    cost[2][9] = 1;
    cost[2][10] = 2;
    cost[2][11] = 2;
    cost[2][12] = 2;
    cost[2][13] = 3;
    cost[2][14] = 2;
    cost[2][15] = 2;

    //cost for player 3
    cost[3][0] = 1;
    cost[3][1] = 2;
    cost[3][2] = 1;
    cost[3][3] = 2;
    cost[3][4] = 1;
    cost[3][5] = 2;
    cost[3][6] = 1;
    cost[3][7] = 2;
    cost[3][8] = 2;
    cost[3][9] = 1;
    cost[3][10] = 3;
    cost[3][11] = 2;
    cost[3][12] = 2;
    cost[3][13] = 1;
    cost[3][14] = 3;
    cost[3][15] = 2;
 }

  public void printCost()
  {
    for(int i = 0; i < 4; i++){
      System.out.print("Cost for player " + i + ": (");
      for(int j = 0; j < 16; j++){
        System.out.print(cost[i][j] + " ");
      }
      System.out.println(")");
    }
  }

  public void initialize(Player[] player)
  {
    int other = random.nextInt(7);

    for(int i = 0; i < 4; i++){
      player[i].printPlayer();
      player[i].printCorrespond();

      if(player[i].getCost(cost, other, 0) < player[i].getCost(cost, other, 1))
      {
        player[i].setFirstStrategy(player[i].getCorrespond(0, other));
	player[i].setPreStrategy(0);
      }else{
        player[i].setFirstStrategy(player[i].getCorrespond(1, other));
	player[i].setPreStrategy(1);
      }

      player[i].setAvgCost0(player[i].getCost(cost, other, 0));
      player[i].setAvgCost1(player[i].getCost(cost, other, 1));
    }
    
  }

  public int findStrategy(int a, int b, int c, int d)
  {
    int[] strat = {a, b, c, d};
    boolean bool;

    for(int i = 0; i < 16; i++){
      bool = true;
      for(int j = 0; j < 4; j++){
        if(strategy[j][i] != strat[j]){
	  bool = false;
	}else{
	  bool = bool && true;
	}
      }
      if(bool){
        return i;
      }
    }

    return -1;
  }

  public void computeAvgCost(Player i, double stage, int other)
  {
    double cost0 = (stage-1.0)/stage * i.getAvgCost0() + 
             1.0/stage * i.getCost(cost, other, 0);
    double cost1 = (stage-1.0)/stage * i.getAvgCost1() + 
             1.0/stage * i.getCost(cost, other, 1);

    i.setAvgCost0(cost0);
    i.setAvgCost1(cost1);

  }
 
  public static void main(String [ ] args)
  {
    JSFP simul = new JSFP();
    Player[] player = new Player[4];

    simul.setStrategy();
    simul.printStrategy();

    simul.setCost();
    simul.printCost();

    int stage = 0;

    for(int i = 0; i < 4; i++){
      player[i] = new Player(strategy, i);
    }
    simul.initialize(player);

    int l = LIMIT;
    int m = LIMIT;
    while(m!= 0){  
      stage++;
      
      for(int i = 0; i < 4; i++){
        System.out.println(player[i].getPreStrategy());
      }

      int a = player[0].getPreStrategy();
      int b = player[1].getPreStrategy();
      int c = player[2].getPreStrategy();
      int d = player[3].getPreStrategy();

      int preStrategy = simul.findStrategy(a,b,c,d);
      System.out.println("Pre-strategy = " + preStrategy);

      for(int i = 0; i < 4; i++){
	int other = player[i].getCorresReverse(preStrategy);

	//compute average cost using strategy 0 and 1
        simul.computeAvgCost(player[i], stage, other);	

        if(player[i].getAvgCost0() < player[i].getAvgCost1()){
	  if(player[i].getPreStrategy() == 0){
	    l--;
	  }else{
	    l = LIMIT;
	  }

	  player[i].setPreStrategy(0);
	}else{
	  if(player[i].getPreStrategy() == 1){
	    l--;
	  }else{
	    l = LIMIT;
	  }

	  player[i].setPreStrategy(1);
	}

      }

      m--;
    }

  }

}
