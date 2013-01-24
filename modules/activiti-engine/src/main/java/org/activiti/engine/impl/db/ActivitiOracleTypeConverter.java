package org.activiti.engine.impl.db;

import liquibase.database.Database;
import liquibase.database.core.H2Database;
import liquibase.database.structure.type.BigIntType;
import liquibase.database.structure.type.CustomType;
import liquibase.database.structure.type.DataType;
import liquibase.database.structure.type.DateTimeType;
import liquibase.database.structure.type.DoubleType;
import liquibase.database.structure.type.FloatType;
import liquibase.database.structure.type.IntType;
import liquibase.database.structure.type.NVarcharType;
import liquibase.database.structure.type.SmallIntType;
import liquibase.database.typeconversion.core.OracleTypeConverter;

public class ActivitiOracleTypeConverter extends OracleTypeConverter {
  
  @Override
  public int getPriority() {
    return super.getPriority() + 1;
  }

  @Override
  public boolean supports(final Database database) {
    return database instanceof H2Database;
  }

  @Override
  protected DataType getDataType(final String columnTypeString,
      final Boolean autoIncrement, final String dataTypeName,
      final String precision, final String additionalInformation) {

    DataType dataType = super.getDataType(columnTypeString, autoIncrement,
        dataTypeName, precision, additionalInformation);
    if (dataType instanceof NVarcharType) {
      try {
        int intPrecision = Integer.valueOf(precision);
        if (intPrecision > 2000) {
          dataType.setFirstParameter("2000");
        }
      } catch(Exception e) {}
    } else if (dataType instanceof CustomType && columnTypeString.toUpperCase().equals("REAL")) {
      dataType = getFloatType();
    }
    return dataType;
  }

  @Override
  public SmallIntType getSmallIntType() {
    return new SmallIntType("NUMBER(5)");
  }

  @Override
  public DateTimeType getDateTimeType() {
    return new DateTimeType("TIMESTAMP(9)");
  }

  @Override
  public IntType getIntType() {
    return new IntType("NUMBER(10)");
  }

  @Override
  public BigIntType getBigIntType() {
    return new BigIntType("NUMBER(19)");
  }

  @Override
  public DoubleType getDoubleType() {
    return new DoubleType("BINARY_DOUBLE");
  }

  @Override
  public FloatType getFloatType() {
    return new FloatType("BINARY_FLOAT");
  }
}
