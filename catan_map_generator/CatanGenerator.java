import java.util.*;


/**
 * CatanGenerator
 */
public class CatanGenerator {
    String[] BoardTiles;
    String[] NumberTiles;
    //Range parameters for the tiles on a board for a 4 player game, these are used when generating the map
    private String desertTile;

    public String getDesertTile(){
        return desertTile;
    }

    private void setDesertTile(String newDesertTile){
        this.desertTile = newDesertTile;
    }

    public CatanGenerator(){
        BoardTiles = new String[]{"R","R","R","B","B","B","T","T","T","T","W","W","W","W","S","S","S","S","D"};
        NumberTiles = new String[]{"2","3","3","4","4","5","5","6","6","8","8","9","9","10","10","11","11","12"};
    }

    public void printMap(String[][] map){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
           }
    }


    public String[][] generateMap(String[] tiles){
        
        String[][] map = new String[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(i==0 && j==0){
                    map[i][j] = " ";
                }
                if(i==0 && j==1){
                    map[i][j] = " ";
                }
                if(i==1 && j==0){
                    map[i][j] = " ";
                }
                if(i==3 && j==0){
                    map[i][j] = " ";
                }
                if(i==4 && j==0){
                    map[i][j] = " ";
                }
                if(i==4 && j==1){
                    map[i][j] = " ";
                }
            }
        }

        return map;
    }

    public String[][] generateTiles(String[][] map, String[] tiles){
        //Generate Resource Map
        Integer index = 0;
        String[] shuffledTiles = shuffleTiles(tiles);
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(map[i][j] != " "){
                    if(shuffledTiles[index] == "D"){
                        String coord = Integer.toString(i) + "," + Integer.toString(j);
                        setDesertTile(coord);
                    }
                    map[i][j] = shuffledTiles[index];
                    index++;
                }
            }
        }

        return map;
    }

    public String[][] generateNumberTiles(String[][] map, String[] tiles) {
        //Generate Number Tiles such that no 6 or 8 tiles are touching
        CatanGenerator catan = new CatanGenerator();
        Integer index = 0;
        String[] shuffledTiles = shuffleTiles(tiles);

        desertTile = getDesertTile();
        String[] coords = desertTile.split(",");
        map[Integer.valueOf(coords[0])][Integer.valueOf(coords[1])] = "7";

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(map[i][j] != " " && map[i][j] != "7"){
                   
                    boolean checker = false;
                    
                    while(checker == false){
                        
                        // System.out.println("number:" + number);
                        
                        if(shuffledTiles[index] == "8" || shuffledTiles[index] == "6"){
                            
                            checker = numberChecker(i, j, map);
                            // System.out.println("Checker:" + checker + " i:" + i + " j:" + j);
                            // catan.printMap(map);

                            if(checker == true){
                                map[i][j] = shuffledTiles[index];
                                index++;

                            } else if (checker == false){
                                map = tileSwapper(map, shuffledTiles, index, i, j);
                                checker = true;

                            }

                        } else {
                            checker = true;
                            map[i][j] = shuffledTiles[index];
                            index++;
                        }
                        catan.printMap(map);
                    }   
                }
            }
        }

        return map;
    }

    public static boolean numberChecker(int i, int j, String[][] map){
        
        if(i==0 && j>1 && j<4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i][j+1] == "6" || map[i][j+1] == "8")
                return false;
            if(map[i+1][j-1] == "6" || map[i+1][j-1] == "8")
                return false;
            if(map[i+1][j] == "6" || map[i+1][j]== "8")
                return false;
        } else if (i==0 && j==4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i+1][j-1] == "6" || map[i+1][j-1] == "8")
                return false;
            if(map[i+1][j] == "6" || map[i+1][j] == "8")
                return false;
        }
        if(i==1 && j>0 && j<4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i][j+1] == "6" || map[i][j+1] == "8")
                return false;
            if(map[i-1][j] == "6" || map[i-1][j] == "8")
                return false;
            if(map[i-1][j+1] == "6" || map[i-1][j+1] == "8")
                return false;
            if(map[i+1][j-1] == "6" || map[i+1][j-1] == "8")
                return false;
            if(map[i+1][j] == "6" || map[i+1][j] == "8")
                return false;
        } else if (i==1 && j==4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i+1][j-1] == "6" || map[i+1][j-1] == "8")
                return false;
            if(map[i+1][j] == "6" || map[i+1][j] == "8")
                return false;
            if(map[i-1][j] == "6" || map[i-1][j] == "8")
                return false;
        }
        if(i==2 && j==0){
            if(map[i][j+1] == "6" || map[i][j+1] == "8")
                return false;
            if(map[i-1][j+1] == "6" || map[i-1][j+1] == "8")
                return false;
            if(map[i+1][j+1] == "6" || map[i+1][j+1] == "8")
                return false;
        } else if (i==2 && j>0 && j<4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i][j+1] == "6" || map[i][j+1] == "8")
                return false;
            if(map[i-1][j] == "6" || map[i-1][j] == "8")
                return false;
            if(map[i-1][j+1] == "6" || map[i-1][j+1] == "8")
                return false;
            if(map[i+1][j-1] == "6" || map[i+1][j-1] == "8")
                return false;
            if(map[i+1][j] == "6" || map[i+1][j] == "8")
                return false;
        } else if (i==2 && j==4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i-1][j] == "6" || map[i-1][j] == "8")
                return false;
            if(map[i+1][j] == "6" || map[i+1][j] == "8")
                return false;
        }
        if(i==3 && j>0 && j<4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i][j+1] == "6" || map[i][j+1] == "8")
                return false;
            if(map[i-1][j] == "6" || map[i-1][j] == "8")
                return false;
            if(map[i-1][j-1] == "6" || map[i-1][j-1] == "8")
                return false;
            if(map[i+1][j-1] == "6" || map[i+1][j-1] == "8")
                return false;
            if(map[i+1][j] == "6" || map[i+1][j] == "8")
                return false;
        } else if (i==3 && j==4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i-1][j-1] == "6" || map[i-1][j-1] == "8")
                return false;
            if(map[i-1][j] == "6" || map[i-1][j] == "8")
                return false;
            if(map[i+1][j] == "6" || map[i+1][j] == "8")
                return false;
        }
        if(i==4 && j>1 && j<4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i][j+1] == "6" || map[i][j+1] == "8")
                return false;
            if(map[i-1][j-1] == "6" || map[i-1][j-1] == "8")
                return false;
            if(map[i-1][j] == "6" || map[i-1][j] == "8")
                return false;
        } else if (i==4 && j==4){
            if(map[i][j-1] == "6" || map[i][j-1] == "8")
                return false;
            if(map[i-1][j-1] == "6" || map[i-1][j-1] == "8")
                return false;
            if(map[i-1][j] == "6" || map[i-1][j] == "8")
                return false;
        }

        return true;
    }
    

    public static String[][] tileSwapper(String[][] map, String[] shuffledTiles, Integer currentIndex, int currentI, int currentJ){

        //will get caught in infinite loop if 6 and 8 are next to each other in loop
        Integer index = 0;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(map[i][j] != " " && map[i][j] != "7" && index<currentIndex){
                    if(map[i][j] != "6" && map[i][j] != "8"){
                        boolean checker = numberChecker(i, j, map);

                        if(checker == true){
                            System.out.println("currentIndex value:" + shuffledTiles[currentIndex]);
                            System.out.println("Index value:" + shuffledTiles[index]);
                            map[i][j] = shuffledTiles[currentIndex];
                            map[currentI][currentJ] = shuffledTiles[index];
                            break;
                        } else {
                            index++;
                        }
                    } else {
                        index++;
                    }
                } else {
                    break;
                }
            }
        }
        

        return map;
    }

    public static String[] shuffleTiles(String[] tiles){
        //get random number
        
        List<String> shuffledTiles = Arrays.asList(tiles);

        Collections.shuffle(shuffledTiles);
        
        shuffledTiles.toArray(tiles);
        //generate random number and populate an array with indexListes
       
        return tiles;
    } 
}


/**
 * main
 */
class main {
    public static void main(String[] args) {
        CatanGenerator catan = new CatanGenerator();
        String[] boardTiles = catan.BoardTiles;
        String[] numberTiles = catan.NumberTiles; 
        //get length of number and board tiles and pass that into the method
        String[][] resourceMap = catan.generateMap(boardTiles);
        resourceMap = catan.generateTiles(resourceMap, catan.BoardTiles);
        String[][] numberMap = catan.generateMap(numberTiles);
        numberMap = catan.generateNumberTiles(numberMap, catan.NumberTiles);
        
        catan.printMap(resourceMap);
        catan.printMap(numberMap);
 
     }
    
}

