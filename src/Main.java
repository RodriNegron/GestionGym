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
                    menu(gym);
                    break;

                case 2:
                    scann.reset();
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

    public static void menu(Gym gym) {
        Scanner scann = new Scanner(System.in);
        int number;
        char var = 's';

        Customer client;
        client = gym.checkClient();

        if (client != null) {
            if (client.getFirstName() != "admin") {
                do {
                    System.out.println("Bienvenido " + client.getFirstName() + "!:D");
                    System.out.println("1-Inscribirse");
                    System.out.println("2-Consutlar turnos disponibles");
                    System.out.println("3-Ingresar dinero a su billetera");
                    System.out.println("4-Consultar monto");
                    System.out.println("5-Consultar estado de cuenta");

                    number = scann.nextInt();

                    /*switch (number) {
                        case 1:
                            gym.alquilarPelicula(client);
                            break;
                        case 2:
                            gym.devolverPelicula(client);
                            break;
                        case 3:
                            ListadoBoletas auxiliar = gym.consultaPeliculasAlquiladas(client);
                            if (client.getTickets().retornarCantEnAlquilerCliente() == 0)
                                System.out.println("No se encontraron peliculas en alquiler");
                            else
                                client.getTickets().consultarListadoBoletas();
                            break;
                        default:
                            System.out.println("Usted ha intentado consultar un valor erroneo");
                    }
                    System.out.println("Desea continuar operando? " + client.getFirstName() + "?" + "s/n");
                    scann.nextLine();
                    var = scann.nextLine().charAt(0);
                } while (var == 's');
            }
            else {
                do {
                    System.out.println("Bienvenido " + client.getNombre() + "!:D");
                    System.out.println("1-Consultar alquileres vigentes");
                    System.out.println("2-Devoluciones del dia");
                    System.out.println("3-Consultar ultimos alquileres de un cliente");
                    System.out.println("4-Titulos mas alquilados");
                    System.out.println("5-Mostrar clientes registrados");
                    System.out.println("Elija una opcion: ");
                    number = scann.nextInt();
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
                    }*/
                    System.out.println("Desea continuar operando? " + client.getFirstName() + "?" + "s/n");
                    scann.nextLine();
                    var = scann.nextLine().charAt(0);
                } while (var == 's');
            }
        }else   System.out.println("Cliente no registrado!");
    }

}

