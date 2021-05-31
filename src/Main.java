import Classes.Abstract.Activity;
import Classes.Aerobic;
import Classes.Crossfit;
import Classes.Customer;
import Classes.Shift;
import Collections.Shifts_map;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        Customer cust1 = new Customer("38606789", "Alessandro", "Casella", "javi_casella95@outlook.com.ar", "Qwer1379");

        Shift shift1 = new Shift("24/02/71995", "15:30", "Musculacion");

        cust1.getWallet().deposit(500);

        cust1.getShifts().addShiftToClient(shift1);

        System.out.println(cust1);
        */

        //gym.consultShifts();

        loggin();

    }


    public static void loggin(){
        Scanner scann = new Scanner(System.in);
        Customer cust;

        Gym gym = new Gym("Forza", "La 39-Mar del Plata", "3120492");
        gym.harcodeShifts();
        gym.hardcodeUsers();

        int number;
        char var = 's';
        String string;


        System.out.println("Welcome to the gym: " + gym.getName());
        do {
            System.out.println("1- Logg-In");
            System.out.println("2- Register");

            number = scann.nextInt();
            scann.nextLine();

            switch (number) {
                case 1:
                   // menu(VS);

                    break;

                case 2:
                    cust = gym.register(scann);
                    gym.addToCustomerList(cust);
                    gym.consultClients();
                    break;

                default:
                    System.out.println("Wrong data!");
            }

            System.out.println("Do you want to continue s/n");
            var = scann.nextLine().charAt(0);

        } while (var == 's');

    }


    /*
    public static void menu(VideoStore VS) {
        int number;
        char var = 's';

        Cliente cli;
        cli = VS.consultaYRetornaCliente();

        if (cli != null) {
            if (cli.getNombre() != "admin") {
                do {
                    System.out.println("Bienvenido " + cli.getNombre() + "!:D");
                    System.out.println("1-Alquilar pelicula");
                    System.out.println("2-Devolver pelicula");
                    System.out.println("3-Consultar peliculas alquiladas");
                    System.out.println("4-Consultar por titulo/genero");

                    number = globalScann.nextInt();

                    switch (number) {
                        case 1:
                            VS.alquilarPelicula(cli);
                            break;
                        case 2:
                            VS.devolverPelicula(cli);
                            break;
                        case 3:
                            ListadoBoletas auxiliar = VS.consultaPeliculasAlquiladas(cli);
                            if (cli.getTickets().retornarCantEnAlquilerCliente() == 0)
                                System.out.println("No se encontraron peliculas en alquiler");
                            else
                                cli.getTickets().consultarListadoBoletas();
                            break;
                        default:
                            System.out.println("Usted ha intentado consultar un valor erroneo");
                    }
                    System.out.println("Desea continuar operando? " + cli.getNombre() + "?" + "s/n");
                    globalScann.nextLine();
                    var = globalScann.nextLine().charAt(0);
                } while (var == 's');
            }
            else {
                do {
                    System.out.println("Bienvenido " + cli.getNombre() + "!:D");
                    System.out.println("1-Consultar alquileres vigentes");
                    System.out.println("2-Devoluciones del dia");
                    System.out.println("3-Consultar ultimos alquileres de un cliente");
                    System.out.println("4-Titulos mas alquilados");
                    System.out.println("5-Mostrar clientes registrados");
                    System.out.println("Elija una opcion: ");
                    number = globalScann.nextInt();
                    switch (number) {
                        case 1:
                            System.out.println(VS.consultarAlquileresVigentes());
                            break;
                        case 2:
                            System.out.println(VS.consultarDevolucionesDiaDeHoy());
                            break;
                        case 3:
                            System.out.println(VS.consultarUltimosDiezAlq());
                            break;
                        case 4:
                            VS.consultarPeliculaMasPopular();
                            break;
                        case 5:
                            VS.consultarClientesRegistrados();
                            break;
                        default:
                            System.out.println("Usted ha intentado consultar un valor erroneo");
                    }
                    System.out.println("Desea continuar operando? " + cli.getNombre() + "?" + "s/n");
                    globalScann.nextLine();
                    var = globalScann.nextLine().charAt(0);
                } while (var == 's');
            }
        }else   System.out.println("Cliente no registrado!");
    }
      */
}

