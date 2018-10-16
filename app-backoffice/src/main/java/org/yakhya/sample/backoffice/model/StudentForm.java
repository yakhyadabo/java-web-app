package org.yakhya.sample.backoffice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateOfBirth;
  private Education education;
  private List<NationalityRow> nationalityList;
  private String countryCode;
  private Boolean scholarshipHolder;
}
