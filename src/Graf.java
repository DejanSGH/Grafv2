import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graf {
    private int brojCvorova;

    private LinkedList<Veza>[] listeSusedstva;

    public Graf(int brojCvorova) {
        this.brojCvorova = brojCvorova;
        listeSusedstva = new LinkedList[brojCvorova];
        for (int i = 0; i < brojCvorova; i++) {
            listeSusedstva[i] = new LinkedList<>();
        }
    }

    public void dodajGranu(int izvor, int destinacija, int tezina){
        listeSusedstva[izvor].add(new Veza(destinacija, tezina));
        listeSusedstva[destinacija].add(new Veza(izvor, tezina));
    }

    public int getBrojCvorova() {
        return brojCvorova;
    }

    public LinkedList<Veza>[] getListeSusedstva() {
        return listeSusedstva;
    }

    public void prikaziGraf(){
        for (int i = 0; i < brojCvorova; i++) {
            System.out.print(i+": ");
            for (Veza v : listeSusedstva[i]) {
                System.out.print(v.destinacija + "(" + v.tezina + ") ");
            }
            System.out.println();
        }
    }

    public void bfs(int pocetni){ // po sirini.
        System.out.println("BFS iz cvora " + pocetni);
        boolean[] otkriven = new boolean[brojCvorova];
        LinkedList<Integer> otkriveni = new LinkedList<>();
        otkriven[pocetni] = true;
        otkriveni.add(pocetni);
        while(!otkriveni.isEmpty()){
            int trenutni = otkriveni.poll();
            System.out.print(trenutni + " ");
            for(Veza v : listeSusedstva[trenutni]){
                if(!otkriven[v.destinacija]){
                    otkriven[v.destinacija] = true;
                    otkriveni.add(v.destinacija);
                }
            }
        }
    }

    public void dfs(int pocetni){ // po dubini.
        boolean[] poseceni = new boolean[brojCvorova];
        System.out.println("DFS iz cvora " + pocetni);
        dfsPoseti(pocetni, poseceni);
        for (int i = 0; i < brojCvorova; i++) {
            if(!poseceni[i]){
                System.out.println("Cvor " + i + " nije moguce posetiti iz cvora " + pocetni);
            }
        }
    }

    private void dfsPoseti(int cvor, boolean[] poseceni){
        poseceni[cvor] = true;
        System.out.print(cvor + " ");
        for(Veza v : listeSusedstva[cvor]){
            if(!poseceni[v.destinacija]){
                dfsPoseti(v.destinacija, poseceni);
            }
        }
    }

    public void dajkstra(int izvor){
        PriorityQueue<CvorDajkstra> pq = new PriorityQueue<>(brojCvorova, Comparator.comparingInt(CvorDajkstra::getDistanca));
        int[] distance = new int[brojCvorova];
        int[] prethodnici = new int[brojCvorova];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[izvor] = 0;
        prethodnici[izvor] = izvor;
        pq.add(new CvorDajkstra(izvor, 0));

        while(!pq.isEmpty()){
            int trenutni = pq.poll().getCvor(); // trenutni je indeks cvora do kojeg nam je putanja najkraca.

            for (Veza v : listeSusedstva[trenutni]) {
                int destinacija = v.destinacija;
                int tezina = v.tezina; // distanca od trenutnog do destinacije.
                if(distance[trenutni] + tezina < distance[destinacija]){
                    pq.add(new CvorDajkstra(destinacija, distance[trenutni] + tezina));
                    distance[destinacija] = distance[trenutni] + tezina;
                    prethodnici[destinacija] = trenutni;
                }

            }
        }

        System.out.println("Iz cvora " + izvor + ": ");
        for (int i = 0; i < brojCvorova; i++) {
            if(i != izvor){
                System.out.println(i + ": distanca je " + distance[i] + " preko cvora " + prethodnici[i]);
            }
        }
    }

    private static class CvorDajkstra{

        private final int cvor;
        private final int distanca;

        public CvorDajkstra(int cvor, int distanca) {
            this.cvor = cvor;
            this.distanca = distanca;
        }

        public int getCvor() {
            return cvor;
        }

        public int getDistanca() {
            return distanca;
        }
    }

    private static class Veza{
        private final int destinacija;
        private final int tezina;

        public Veza(int destinacija, int tezina) {
            this.destinacija = destinacija;
            this.tezina = tezina;
        }
        public int getDestinacija() {
            return destinacija;
        }

        public int getTezina() {
            return tezina;
        }
    }
}
