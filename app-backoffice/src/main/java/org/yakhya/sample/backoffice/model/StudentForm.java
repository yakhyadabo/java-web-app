package org.yakhya.sample.backoffice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.yakhya.sample.domain.enums.Education;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(of = { "personalNumber" })
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentForm {
  private String personalNumber;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private Education education;
  private List<NationalityForm> nationalityList;
  private NationalityForm nationality;
  private Boolean scholarshipHolder;
}
