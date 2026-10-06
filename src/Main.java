//Изучите методы isAlive() и join(). (самостоятельно!! поиск в интернете вам в помощь)
//Разработать приложение, позволяющее разрешить спор: "Что появилось сначала - яйцо или курица?".
//Один поток должен выводить сообщение "Курица",
//другой поток должен выводить сообщение "Яйцо".
//В результате работы приложения должны выводиться результаты спора,
//в зависимости от того, какой поток сказал последнее слово.
//Результат:
//Курица
//Яйцо
//Курица
//Яйцо
//Курица
//Яйцо
//Курица
//Яйцо
//Яйцо
//Курица
//Спор окончен. Победила Курица
//Process finished with exit code 0

class Chiken extends Thread{//поток
    public Chiken(String chicken){//конструктор
       super(chicken);//обращение к thread к родителю
    }
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("курица");
            Main.winner = "курица";//перезаписывает winner на свое значние потом ожидает вдругой поток
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();//среда разработки так поставила
            }
        }
    }
}
class Egg extends Thread{//и это поток тоже
    public Egg(String egg){//конструктор
        super(egg);//тоже обращение
    }
    public void run(){
        for (int i = 0; i < 5; i++) {//перезаписывает тоже и так 5 раз
            System.out.println("яйцо");
            Main.winner = "яйцо";
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();//среда разработки так поставила
            }
        }
    }
}
public class Main {
    public static String winner = "";

    public static void main(String[] args) {
        Chiken chiken = new Chiken("курица");
        Egg egg = new Egg("яйцо");
        chiken.start();//парарллеьно спорят
        egg.start();
        try {
            while (chiken.isAlive() && egg.isAlive()){
                Thread.sleep(20);
            }
            chiken.join();//ждет завершение потока
            egg.join();// и этого тоже
            System.out.println("спор окончен, победа:" + winner);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);//среда разработки так поставила
        }
        System.out.println("основной поток завершает работу.");//(для того чтобы я наглядно видела завершение работы)
    }
}