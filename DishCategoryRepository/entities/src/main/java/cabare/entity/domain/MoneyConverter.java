package cabare.entity.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MoneyConverter implements AttributeConverter<Money, Long> {

  @Override
  public Long convertToDatabaseColumn(Money money) {
    return money.getRawValue();
  }

  @Override
  public Money convertToEntityAttribute(Long dbData) {
    return dbData == null ? Money.ZERO : Money.valueOf(dbData);
  }
}
