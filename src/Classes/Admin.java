package Classes;


import Classes.Abstract.Person;
import Classes.Abstract.Training_plan;
import Collections.*;
import Classes.premiumPlan;
import Classes.basicPlan;





    public class Admin extends Person{
        private Shifts_map shifts_map;
        private Customer_list customers_list;
        private TrainingPlan_list training_plan_list;
        private Activity_list activities_list;
        private Instructor_list instructor_list;



        //region constructor
        public Admin() {
        }
        public Admin(String email, String password) {
            super(email, password);

        }

        public Admin(String dni, String firstName, String lastName, String email, String password) {
            super(dni, firstName, lastName, email, password);
            this.shifts_map = shifts_map;
            this.customers_list = customers_list;
            this.training_plan_list = training_plan_list;
            this.activities_list = activities_list;
            this.instructor_list = instructor_list;
        }


        //endregion


        //get and set




}
