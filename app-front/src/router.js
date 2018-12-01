import Vue from "vue";
import Router from "vue-router";
import CustomersList from "./components/customer/List.vue";
import AddCustomer from "./components/customer/Add.vue";
import SearchCustomers from "./components/customer/Search.vue";
import Customer from "./components/customer/Details.vue";
 
Vue.use(Router);
 
export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "customers",
      alias: "/customer",
      component: CustomersList,
      children: [
        {
          path: "/customer/:id",
          name: "customer-details",
          component: Customer,
          props: true
        }
      ]
    },
    {
      path: "/add",
      name: "add",
      component: AddCustomer
    },
    {
      path: "/search",
      name: "search",
      component: SearchCustomers
    }
  ]
});
