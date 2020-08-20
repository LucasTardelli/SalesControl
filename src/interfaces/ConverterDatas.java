
package interfaces;

import java.util.Date;

/**
 *
 * @author Aragao
 */
public class ConverterDatas {
    private java.sql.Date dtSQL;
    private Date dtUTIL;
    
    public java.sql.Date convertDataSQL(Date dataUTIL){
        this.dtSQL = new java.sql.Date(dataUTIL.getTime());
        return dtSQL;
    }
    
}
