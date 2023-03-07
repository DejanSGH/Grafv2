public class Main {

    public static void main(String[] args) {


        Graf graf = new Graf(10);

        graf.dodajGranu(0,1,1);
        graf.dodajGranu(0,2,6);
        graf.dodajGranu(1,0,1);
        graf.dodajGranu(1,3,3);
        graf.dodajGranu(1,5,5);
        graf.dodajGranu(2,0,6);
        graf.dodajGranu(2,5,8);
        //graf.dodajGranu(2,4,2);
        graf.dodajGranu(3,1,3);
        graf.dodajGranu(3,7,7);
        //graf.dodajGranu(4,2,2);
        graf.dodajGranu(4,5,3);
        //graf.dodajGranu(4,6,2);
        graf.dodajGranu(5,1,5);
        graf.dodajGranu(5,2,8);
        graf.dodajGranu(5,4,3);
        //graf.dodajGranu(6,4,2);
        graf.dodajGranu(6,7,9);
        graf.dodajGranu(6,8,4);
        graf.dodajGranu(7,3,7);
        graf.dodajGranu(7,6,9);
        graf.dodajGranu(7,8,3);
        graf.dodajGranu(8,7,3);
        graf.dodajGranu(8,6,4);

        //graf.dajkstra(0);

        graf.dfs(5);
        graf.bfs(5);

    }
}