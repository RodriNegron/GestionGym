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


            public Shifts_map getShifts_map() {
                return shifts_map;
            }

            public void setShifts_map(Shifts_map shifts_map) {
                this.shifts_map = shifts_map;
            }

            public Customer_list getCustomers_list() {
                return customers_list;
            }

            public void setCustomers_list(Customer_list customers_list) {
                this.customers_list = customers_list;
            }

            public TrainingPlan_list getTraining_plan_list() {
                return training_plan_list;
            }

            public void setTraining_plan_list(TrainingPlan_list training_plan_list) {
                this.training_plan_list = training_plan_list;
            }

            public Activity_list getActivities_list() {
                return activities_list;
            }

            public void setActivities_list(Activity_list activities_list) {
                this.activities_list = activities_list;
            }

            public Instructor_list getInstructor_list() {
                return instructor_list;
            }

            public void setInstructor_list(Instructor_list instructor_list) {
                this.instructor_list = instructor_list;
            }
        //endregion



            /*public double consultEarnings() {
                double earns = 0;
                int id = 0;

                if (customers_list.size() > 0) {
                    for (Customer c : customers_list) {
                        id = c.getTraining_Plan();
                        if(id == 1){
                            earns = earns + c.
                        }
                        else if (id == 2){
                            earns = earns +
                        }


                    }
                }

                return earns;

            }
            */

            @Override
            public String toString() {
                return super.toString() +
                        "/n Admin{}";
            }
        }
