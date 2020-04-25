/*     */ package br.mat.mmgalvao.common.aleph.dao;
import br.mat.mmgalvao.common.aleph.Column;
import br.mat.mmgalvao.common.aleph.ColumnInfo;
import br.mat.mmgalvao.common.aleph.DataSet;
import java.io.Serializable;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.math.BigInteger;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;

/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
/*     */ 
/*     */ public class DbServerDAO {
             DatabaseMetaData metada=null;
/*     */   public DbServerDAO(Connection con) {
/*  16 */     this.con = con;
                 try {
                     this.metada=con.getMetaData();
                 } catch (SQLException ex) {
                     Logger.getLogger(DbServerDAO.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }

    public String getDatabaseProductName() {
        try{
            return metada.getDatabaseProductName();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage(),ex);
        }
    }

    public String getDatabaseProductVersion()  {
        try{    
        return metada.getDatabaseProductVersion();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage(),ex);
        }
    }

    public DataSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)  {
        DataSet dt = new DataSet();
        try {
        
        getData(metada.getTables(catalog, schemaPattern, tableNamePattern, types),dt);
        return dt;
        }
        catch(SQLException sqle){
            
/*  48 */     
                  
                 System.out.println(String.valueOf(sqle.getErrorCode()));
/*  50 */       System.out.println(sqle.getMessage());
/*  51 */       dt.setSucess(false);
/*  52 */       dt.setErrorCode(sqle.getErrorCode());
/*     */      
        }
        return dt;
    }
    public DataSet getImportedKeys(String catalog, String schema, String table) {
        DataSet dt = new DataSet();
        try {
        
        getData(metada.getImportedKeys(catalog, schema, table),dt);
        return dt;
        }
        catch(SQLException sqle){
            
/*  48 */     
                  
                 System.out.println(String.valueOf(sqle.getErrorCode()));
/*  50 */       System.out.println(sqle.getMessage());
/*  51 */       dt.setSucess(false);
/*  52 */       dt.setErrorCode(sqle.getErrorCode());
/*     */      
        } 
        return dt;
       
    }

    public  DataSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)  {
       
         DataSet dt = new DataSet();
        try {
        
            getData(metada.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern),dt);
            return dt;
        }
        catch(SQLException sqle){
            
/*  48 */     
                  
                 System.out.println(String.valueOf(sqle.getErrorCode()));
/*  50 */       System.out.println(sqle.getMessage());
/*  51 */       dt.setSucess(false);
/*  52 */       dt.setErrorCode(sqle.getErrorCode());
/*     */      
        } 
        return dt;
    }

    public DataSet getExportedKeys(String catalog, String schema, String table){
        DataSet dt = new DataSet();
        try {
        
        getData(metada.getExportedKeys(catalog, schema, table),dt);
        
        }
        catch(SQLException sqle){
            
/*  48 */     
                  sqle.printStackTrace();
                 System.out.println(String.valueOf(sqle.getErrorCode()));
/*  50 */       System.out.println(sqle.getMessage());
/*  51 */       dt.setSucess(false);
/*  52 */       dt.setErrorCode(sqle.getErrorCode());
/*     */      
        } 
       return dt;
    }

    public DataSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) {
        DataSet dt = new DataSet();
        try {
        
        getData(metada.getIndexInfo(catalog, schema, table, unique, approximate),dt);
        
        }
        catch(SQLException sqle){
            
/*  48 */     
                  
                 System.out.println(String.valueOf(sqle.getErrorCode()));
/*  50 */       System.out.println(sqle.getMessage());
/*  51 */       dt.setSucess(false);
/*  52 */       dt.setErrorCode(sqle.getErrorCode());
/*     */      
        } 
       return dt;
        
    }
    
   
    Connection con;
    public void close(){
                 try {   
                    
                     if(this.con!=null){
                         
                        this.con.close();
                        this.con=null;
                     }
                    
                 } catch (SQLException ex) {
                     Logger.getLogger(DbServerDAO.class.getName()).log(Level.SEVERE, null, ex);
                 }
    }
/*     */   public List<ColumnInfo> getMetadataTable(String db, String schema, String table) throws SQLException {
/*  19 */     List<ColumnInfo> result = new ArrayList<>();
/*  20 */     ResultSet rs = this.con.getMetaData().getColumns(db, 
/*  21 */         schema, 
/*  22 */         table, 
/*  23 */         "%");
/*  24 */     getCollumnInfo(rs, result);
/*  25 */     return result;
/*     */   }
/*     */   private void getCollumnInfo(ResultSet rs, List<ColumnInfo> result) throws SQLException {
/*  28 */     while (rs.next()) {
/*  29 */       result.add(analiseColmun(rs, result));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private ColumnInfo analiseColmun(ResultSet rs, List<ColumnInfo> result) {
/*  35 */     return null;
/*     */   }
/*     */   
/*     */   public DataSet execute(String query, Object... params) {
/*  39 */     DataSet dt = new DataSet();
              PreparedStatement st=null;
/*     */     try {
/*  41 */       st = this.con.prepareCall(query);
/*  42 */       createParams(st, params);
/*  43 */       if (st.execute()) {
/*  44 */         getData(st.getResultSet(), dt);
/*     */       }
/*  46 */       dt.setSucess(true);
               // this.con.commit();
/*     */     }
/*  48 */     catch (SQLException sqle) {
                  
                 System.out.println(String.valueOf(sqle.getErrorCode()) + query);
/*  50 */       System.out.println(sqle.getMessage());
/*  51 */       dt.setSucess(false);
/*  52 */       dt.setErrorCode(sqle.getErrorCode());
/*     */     } 
finally{
    try {
   if( st.getResultSet()!=null) {
       
           st.getResultSet().close();
       
   }
    
        st.close();
    } catch (SQLException ex) {
        Logger.getLogger(DbServerDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
/*     */ 
/*     */     
/*  56 */     return dt;
/*     */   }

/*     */   private void createParams(PreparedStatement st, Object[] params) throws SQLException {
/*  60 */     int i = 1; 
/*  61 */     for (Object p:params) { 
/*  62 */       st.setObject(i++, p);
/*     */      }
/*     */   
/*     */   }

/*     */   
/*     */   private void getData(ResultSet rs, DataSet dt) throws SQLException {
/*  68 */     
/*  69 */     getMetadata(rs.getMetaData(), dt);
/*  70 */     getData(rs, rs.getMetaData(), dt);
/*     */   }
/*     */ 
/*     */   
/*     */   private void getMetadata(ResultSetMetaData metaData, DataSet dt) throws SQLException {
/*  75 */       Column [] columns=new Column[ metaData.getColumnCount()];
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
/*  76 */       columns[i-1]=crateColumnInfo(metaData, i);
/*     */     }
                dt.setColumns(columns);
/*     */   }
/*     */   
/*     */   private Column crateColumnInfo(ResultSetMetaData d, int i) throws SQLException {
/*  81 */     Column col = new Column();
/*  82 */     col.setNullAble(d.isNullable(i));
/*  83 */     col.setPrecsion(d.getPrecision(i));
/*  84 */     col.setScale(d.getScale(i));
/*  85 */     col.setPos(i);
/*  86 */     col.setMaxLength(d.getColumnDisplaySize(i));
/*  87 */     col.setName(d.getColumnName(i));
/*  88 */     col.setTypeName(d.getColumnClassName(i));
/*  89 */     return col;
/*     */   }
/*     */ 
/*     */   
/*     */   private void getData(ResultSet rs, ResultSetMetaData d, DataSet dt) throws SQLException {
     List<Object[]> ls = new ArrayList<>();
     
/*  94 */     while (rs.next()) {
/*  95 */       Object[] ls1 =new Object[ d.getColumnCount()];
/*  96 */       for (int i = 1; i <= d.getColumnCount(); i++) {
/*  97 */         Object obj = rs.getObject(i);
/*  98 */         if (obj instanceof String) {
/*  99 */           String str1 = (String)obj;
/* 100 */           obj = str1.trim();
/* 101 */           if (str1.startsWith("!#SERIAL#!")) {
/* 102 */             int l = "!#SERIAL#!       2164800000000000000014E490000000000000002430000000000000050".length();
/*     */             try {
/* 104 */               obj = (new String((new BigInteger(str1.substring(l), 16)).toByteArray(), "ISO-8859-1")).trim();
/* 105 */             } catch (UnsupportedEncodingException e) {
/*     */               
/* 107 */               e.printStackTrace();
/*     */             } 
/*     */           } 
/*     */         } 

                  //System.out.println(i+"-"+d.getColumnCount()+"-"+obj.getClass());
/* 111 */         ls1[i-1]=obj;
/*     */       }  
                ls.add(ls1);
/*     */     } 

                dt.setData(ls.toArray(new Object[0][]));
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws SQLException {
/* 119 */     Connection conn = null;
/* 120 */     ResultSet rs = null;
/* 121 */     String url = "jdbc:sqlserver://127.0.0.1:1433;database=Desenv_RH";
/* 122 */     String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
/* 123 */     String userName = "sa";
/* 124 */     String password = "gabi3010";
/*     */try {       Class.forName(driver);
/* 127 */       conn = DriverManager.getConnection(url, userName, password);
/*     */       
                DbServerDAO dao=new DbServerDAO(conn);
                System.out.println(dao.getExportedKeys("Desenv_RH","dbo","USUARIOS"));
/* 129 */       /*System.out.println((new DbServerDAO(conn)).execute( "SELECT top 1 REFERENCIA,\r\n" + 
    					"      DOMINIO\r\n" + 
    					"	  ,FIELD_NAME\r\n" + 
    					"      ,FIELD_TYPE\r\n" + 
    					"      ,FIELD_LEN\r\n" + 
    					"      ,FIELD_DEC\r\n" + 
    					"      ,DESCRICAO\r\n" + 
    					"      ,DESCR_TELA\r\n" + 
    					"      ,MASCARA\r\n" + 
    					"      ,VALIDACAO\r\n" +   			 
    					"      ,CHAVE\r\n" + 
    					"      ,PRE_PUT\r\n" + 
    					"      ,POS_GET\r\n" + 
    					"      ,[UNIQUE]\r\n" + 
    					"      ,NOTNULL\r\n" + 
    					"      ,EXPR_DEFAU\r\n" + 
    					"      ,HELP\r\n" + 
    					"     \r\n" + 
    					"      \r\n" + 
    					"  FROM dbo.PRODIGY_CAMPOS where  [unique] is not null", new Object[0]));
/*     */       /*
       DatabaseMetaData dbm = conn.getMetaData();
      System.out.println(0);
      System.out.println(1);
       System.out.println(2);
       System.out.println(dbm.getDatabaseProductName());
      System.out.println(NumberFormat.getInstance(new Locale("pt", "BR")));
    System.out.println(dbm.getDatabaseProductVersion());
      rs = dbm.getIndexInfo(null, "dbo", "POSICAO", false, false);
       ResultSetMetaData rsmd = rs.getMetaData();
/       int cols = rsmd.getColumnCount();
       while (rs.next()) {
         for (int i = 1; i <= cols; i++) {
          System.out.println(String.valueOf(rsmd.getColumnName(i)) + "=" + rs.getObject(i));
        }
        System.out.println("_________________________________");
     }

       rs.close();*/
     }
    catch (SQLException e) {
     System.out.println(e.getErrorCode());
       System.out.println(e.getMessage());
    
     }
    catch (Exception ex) {
       ex.printStackTrace();
    } 
     
    if (conn != null) {
      conn.close();
      if (rs != null)
        rs.close(); 
     } 
  }
}


/* Location:              C:\HSMMGWorkSpace\AlephDataBase\lib\AlephDataBase.jar!\br\mat\mmgalvao\dbserver\commons\DbServerDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */