<template>
  <div v-if="this.customer">
    <h4>Customer</h4>
    <div>
      <label>Personal Number: </label> {{this.customer.personalNumber}}
    </div>
    <div>
      <label>Firstname: </label> {{this.customer.firstName}}
    </div>
    <div>
      <label>Lastname: </label> {{this.customer.lastName}}
    </div>

    <div>
      <label>Date of birth: </label> {{this.customer.dateOfBirth}}
    </div>

    <div>
      <label>Scholarship holder: </label> {{this.customer.scholarshipHolder}}
    </div>

    <div>
      <label>Education: </label> {{this.customer.education}}
    </div>
<!--
    <div>
      <label>Active: </label> {{this.customer.active}}
    </div>
  -->
    <span v-if="this.customer.active"
      v-on:click="updateActive(false)"
      class="button is-small btn-primary">Inactive</span>
    <span v-else
      v-on:click="updateActive(true)"
      class="button is-small btn-primary">Active</span>
  
    <span class="button is-small btn-danger" v-on:click="deleteCustomer()">Delete</span>
  </div>
  <div v-else>
    <br/>
    <p>Please click on a Customer...</p>
  </div>
</template>
 
<script>
import http from "../../http-common";
 
export default {
  name: "customer",
  props: ["customer"],
  methods: {
    /* eslint-disable no-console */
    updateActive(status) {
      var data = {
        id: this.customer.personalNumber,
        Firstname: this.customer.firstName,
        Lastname: this.customer.lastName,
        active: status
      };
 
      http
        .put("/customer/" + this.customer.id, data)
        .then(response => {
          this.customer.active = response.data.active;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteCustomer() {
      http
        .delete("/customer/" + this.customer.id)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          this.$router.push('/');
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>
