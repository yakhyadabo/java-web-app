<template>
  <div class="submitform">
    <div v-if="!submitted">
        <div class="form-group">
          <label for="personalNumber">personalNumber</label>
          <input type="text" class="form-control" id="personalNumber" required v-model="customer.personalNumber" personalNumber="personalNumber">
        </div>

        <div class="form-group">
          <label for="firstName">firstName</label>
          <input type="text" class="form-control" id="firstName" required v-model="customer.firstName" firstName="firstName">
        </div>
    
        <div class="form-group">
          <label for="lastName">lastName</label>
          <input type="text" class="form-control" id="lastName" required v-model="customer.lastName" lastName="lastName">
        </div>

<!--
        <div class="form-group">
          <label for="age">Age</label>
          <input type="number" class="form-control" id="age" required v-model="customer.age" name="age">
        </div>
   --> 
        <button v-on:click="saveCustomer" class="btn btn-success">Submit</button>
    </div>
    
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" v-on:click="newCustomer">Add</button>
    </div>
  </div>
</template>
 
<script>
import http from "../../http-common";
 
export default {
  name: "add-customer",
  data() {
    return {
      customer: {
        personalNumber: 0,
        firstName: "",
        lastName: ""
        /* age: 0,
        active: false */
      },
      submitted: false
    };
  },
  methods: {
    /* eslint-disable no-console */
    saveCustomer() {
      var data = {
        firstName: this.customer.firstName,
        lastName: this.customer.lastName,
        personalNumber: this.customer.personalNumber
        /* age: this.customer.age */
      };
 
      http
        .post("/customer", data)
        .then(response => {
          this.customer.personalNumber = response.data.personalNumber;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
          throw e;
        });
 
    },
    newCustomer() {
      this.submitted = false;
      this.customer = {};
    }
    /* eslint-enable no-console */
  }
};
</script>
 
<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
