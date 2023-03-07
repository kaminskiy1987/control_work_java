package ToyStore;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        String inputMessage = "Добро пожаловать в программу Магазин игрушек!\n ";
        String outputMessage = "До скорых встреч!";

        String startMenu = String.join("\n"
                , "Доступные действия:"
                , "1 - Показать все игрушки"
                , "2 - Добавить(изменить количество) игрушку"
                , "3 - Разыграть игрушки"
                , "4 - Выход"
                , "Введите цифру с необходимым действием: ");

        if (!new File(Toy.filename).isFile()) {
            Toy.testData();
        }

        System.out.println(inputMessage);

        while (true) {
            System.out.println(startMenu);
            Scanner in = new Scanner(System.in);
            int choice;

            try {
                choice = in.nextInt();
                in.nextLine();
            } catch (Exception ex) {
                System.out.println("Ввоодить можно только числа...");
                continue;
            }

            if (choice == 1) {
                System.out.println("Cписок всех игрушек: ");
                ArrayList<Toy> newToys = Toy.readFile();

                for (Toy t : newToys)
                    System.out.println(t.toString());

                System.out.println();

            } else if (choice == 2) {
                String name;
                int quantity;
                int frequency;

                while (true) {
                    System.out.println("Введите наименование игрушки: ");

                    try {
                        name = in.nextLine();

                        if (name.equals("")) {
                            System.out.println("Наименование не может быть пустым...");
                            continue;
                        }
                        break;

                    } catch (Exception ex) {
                        System.out.println("Наименование не может быть пустым...");
                    }
                }

                while (true) {
                    System.out.println("Введите количество для данной игрушки: ");

                    try {
                        quantity = in.nextInt();
                        break;
                    } catch (Exception ex) {
                        System.out.println("Ввоодить можно только числа...");
                        in.nextLine();
                    }
                }

                while (true) {
                    System.out.println("Введите частоту выпадения для данной игрушки: ");

                    try {
                        frequency = in.nextInt();
                        if (frequency <= 0 || frequency > 50) {
                            System.out.println("Частота выпадения может лежать в интервале от 1 до 100");
                            continue;
                        }
                        break;

                    } catch (Exception ex) {
                        System.out.println("Ввоодить можно только числа...");
                        in.nextLine();
                    }
                }

                Toy toy1 = new Toy(name, quantity, frequency);
                System.out.println("Игрушка сохранена.");

            } else if (choice == 3) {
                int count = 0;

                while (true) {
                    System.out.println("Введите количество разыгрываемых игрушек: ");

                    try {
                        count = in.nextInt();
                        break;
                    } catch (Exception ex) {
                        System.out.println("Ввоодить можно только числа...");
                        in.nextLine();
                    }
                }

                Toy.getPrizeToy(count);

            } else if (choice == 4) {
                in.close();
                System.out.println(outputMessage);
                break;
            }
        }
    }
}