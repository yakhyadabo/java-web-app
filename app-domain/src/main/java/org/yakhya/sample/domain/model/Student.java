package org.yakhya.sample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.yakhya.sample.domain.enums.Education;

import java.time.LocalDate;

@EqualsAndHashCode(of = { "personalNumber" })
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends UIDBaseEntity{
  private String personalNumber;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private Education education;
  private Nationality nationality;
  private Boolean scholarshipHolder;
}