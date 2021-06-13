package Collections;

import Classes.Abstract.Training_plan;
import Classes.Customer;
import Classes.Instructor;
import Interfaces.Controller;

import java.util.ArrayList;
import java.util.List;

public class TrainingPlan_list implements Controller {
    List<Training_plan> plan_list;

    public TrainingPlan_list()
    {
        plan_list = new ArrayList<>();
    }

    @Override
    public void add(Object name) {
        boolean createdTrainingPlan = false;
        name = (Training_plan) name;
        for (Training_plan i : plan_list) {
            if (((Training_plan) name).equals(i)) {
                createdTrainingPlan=true;
                break;
            }
        }
        if (!createdTrainingPlan) {
            plan_list.add((Training_plan) name);
        } else {
            System.out.println("Este Plan de Entrenamiento ya existe !!");
        }
    }

    @Override
    public void delete(int id) {
        Training_plan tpDelete = (Training_plan) findById(id);
        if (tpDelete!=null){
            plan_list.remove(tpDelete);
        }else{
            System.out.println("Plan de Entrenamiento no encontrado. ");
        }
    }

    @Override
    public void consultList() {
        for (Training_plan i : plan_list) {
            System.out.println(i.toString());
        }
    }

    @Override
    public Object findById(int id) {
        Training_plan tp = null;
        if (plan_list.size() > 0){
            for (Training_plan i : plan_list) {
                if (i.getId() == id) {
                    tp = i;
                    break;
                }
            }
        }
        return tp;
    }

    public void buyTrainingPlan(Customer customer, int trainingPlan)
    {
        Training_plan tp = (Training_plan) findById(trainingPlan);

        if (tp != null) {
            if (customer.getTraining_Plan() == 0) {
                if (customer.getWallet().getTotal_Amount() >= tp.getPrice()) {
                    customer.setTraining_Plan(tp.getId());
                    customer.setDatesTrainingPlan();
                    customer.getWallet().debit(tp.getPrice());

                    System.out.println("Registro completado");
                } else System.out.println("Saldo insuficiente");
            } else System.out.println("Ya se encuentra inscripto a un plan");
        }
        else System.out.println("Opcion invalida!");

    }


}
