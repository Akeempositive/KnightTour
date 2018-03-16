package knighttouranimated;

public class KnightTour{

    private static int[] asNumber(String[] result) {
        int[] resInNum = new int[64];
        int i =0, max=0;
        String k = "ABCDEFGH";
        while(i<64){
            char [] res = result[i].toCharArray();
            int p = Integer.parseInt(""+res[0]);
            resInNum[i]= (p-1)*8+  k.indexOf(res[1]);
            max=Math.max(max, resInNum[i]);
            i++;
        }
        return resInNum;
    }
    int[][] spots= new int[8][8];
     int[][] visited= new int[8][8];
    boolean [][]touches= new boolean[8][8];
    void fillSpot(){
        for(int i=0; i<=7; i++){
            for(int j=0; j<=7; j++){
                spots[i][j]= getPossibleReach(i,j);
            }
        }
    }
    
    void fillTouch(){
        int i=0;
        while(i<=7){
            int j=0;
            while(j<=7){
                touches[i][j]=false;
                j++;
            }
            i++;
        }
    }
    public static void main(String []a){
        start(null);
    }
    public static int[] start(String args[]){
        int x[]= {7,0};
        String col[]= {"A", "B","C","D","E","F","G","H"};
        KnightTour knight= new KnightTour();
        knight.fillTouch();
        knight.fillSpot();
        String[] result=new String[64];
        int i=0;
        result[0]= x[1]+1+ col[x[0]];
        while(i<64){
            result[i]=x[1]+1 + col[x[0]];
            x=knight.move(x);
            System.out.println(" to " + (x[1]+1) + "" + col[x[0]]);
            i++;
        }
        knight.printSpots();
        return asNumber(result);
        
    }
    
   int[] move(int[]x){
        touches[x[0]][x[1]]=true;
        visited[x[0]][x[1]]=1;
        return computeBest(x[0],x[1]);
    }
   
   void printSpots(){
        int i=0;
        int count=0;
        while(i<8){
            int j=0;
            while(j<8){
                if(visited[i][j]==1){
                    count++;
                }
                System.out.print(visited[i][j] + "\t");
                j++;
            }
            System.out.println();
            i++;
        }
        System.out.println("Total touches = " + count);
    }
    
   int getPossibleReach(int i, int j){
         int reach=0;
        int row[] = {-1,-2,1,2};
        int col[]= {-1,-2,1,2};
        for (int a : row){
            for (int b: col){
                if(a%2!=b%2){
                    if((a+b)%2!=0){
                        if(a+i<=7&&a+i>=0&&b+j<=7&&b+j>=0){
                            if(!touches[a+i][b+j]){
                                reach++;
                            }
                        }
                    }
                }
            }
        }
        return reach;
    }
    
   int[] computeBest(int i, int j){
        int []x=new int[2];
        int bestReachable=8;
       int row[] = {-1,-2,1,2};
        int col[]= {-1,-2,1,2};
        for (int a : row){
            for (int b: col){
                if(a%2!=b%2){
                    if((a+b)%2!=0){
                        if(a+i<=7&&a+i>=0&&b+j<=7&&b+j>=0){
                            if(!touches[a+i][b+j]){
                                int move = getPossibleReach(a+i, b+j);
                                if(move<bestReachable){
                                    x[0]=a+i;
                                    x[1]=b+j;
                                    bestReachable=move;
                                }
                            }
                        }
                    }
                }
            }
        }
        return x;
    }
   
}