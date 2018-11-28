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
  private LocalDate dateOfBirth;
  private String education;
  private String countryCode;
  private Boolean scholarshipHolder;
}
