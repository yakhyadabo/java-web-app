package org.yakhya.sample.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(of = { "personalNumber" })
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResource {
  private String personalNumber;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth = LocalDate.of(1990,01,01);
  private String education = "M";
  private NationalityResource nationality = NationalityResource.builder().code("UK").name("United Kingdom").build();
  private Boolean scholarshipHolder;
}
