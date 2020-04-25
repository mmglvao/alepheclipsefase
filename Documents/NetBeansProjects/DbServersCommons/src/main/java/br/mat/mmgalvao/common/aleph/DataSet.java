/*    */ package br.mat.mmgalvao.common.aleph;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
         import java.util.Arrays;
            import java.util.HashMap;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DataSet implements Serializable {
/*    */   int postAtual=-1;
            public int getPosAtual(){
                return this.postAtual;
            }
            public void reset(){
                this.postAtual=-1;
            }
            public String toString() {
/*  8 */    String str="";    

/*  9 */    for(Object[]str1:this.data){
            int i=0;
            for(Column col:   this.columns){
            
             str=str+col.getName()+"="+String.valueOf(str1[i++])+"\n";
                
        }
str=str+"\n";
            
                str=str+"--------------------\n";
            }
/*    */    return str;
/*    */ }  
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   boolean sucess;
/*    */   String errorMessage;
/*    */   int errorCode;
/* 18 */   Column[] columns ;
           HashMap<String,Integer> campos=new HashMap<>();
/* 19 */   Object[][] data;
/*    */   public Column[] getColumns() {
/* 21 */     return this.columns;
/*    */   }
/*    */   public void setColumns(Column[] collumns) {
            //System.out.println("setei colunas");
/* 24 */     this.columns = collumns;   
             createRefs();
/*    */   }        
           public void createRefs(){
               int i=0;
               
            for(Column col : columns){
              //  System.out.println(col.getName().toLowerCase()+"-"+i);
                campos.put(col.getName().toLowerCase(),i++);
            }
           }
           public Object getValue(int i){
               return this.data[this.postAtual][i];
           }
           public int getIntValue(int i){
               Object s=getValue(i);
               if(s instanceof Number){
                   return ((Number)s).intValue();
               }
               throw new RuntimeException("tipo nao é numerico");
           }
           
           public long getLongValue(int i){
               Object s=getValue(i);
               if(s instanceof Number){
                   return ((Number)s).longValue();
               }
               throw new RuntimeException("tipo nao é numerico");
           }
           
           public double getDoubleValue(int i){
               Object s=getValue(i);
               if(s instanceof Number){
                   return ((Number)s).doubleValue();
               }
               throw new RuntimeException("tipo nao é numerico");
           }
           
           public java.sql.Date getDateValue(int i){
               Object s=getValue(i);
               if(s instanceof java.sql.Date){
                   return ((java.sql.Date)s);
               }
               throw new RuntimeException("tipo nao é date");
           }
           public String getStringValue(int i){
               Object s=getValue(i);
               if(s instanceof String){
                   return ((String)s);
               }
               throw new RuntimeException("tipo nao é string");
           }
           public boolean getBoleanValue(int i){
               Object s=getValue(i);
               if(s instanceof Boolean){
                   return ((boolean)s);
               }
               throw new RuntimeException("tipo nao é boolean");
           }
           public Object getValue(String name){
               Integer s=this.campos.get(name.toLowerCase());
               if(s!=null)
                return getValue(s);
               else
                   throw new RuntimeException("Field not find  "+name);
               
           }
           public Long getLongValue(String i){
               Object s=getValue(i);
               if(s==null) return null;
               if(s instanceof Number){
                   return ((Number)s).longValue();
               }
               throw new RuntimeException("tipo nao é numerico");
           }
           
           public Double getDoubleValue(String i){
               Object s=getValue(i);
               if(s==null) return null;
               if(s instanceof Number){
                   return ((Number)s).doubleValue();
               }
               throw new RuntimeException("tipo nao é numerico");
           }
           
           public java.sql.Date getDateValue(String i){
               Object s=getValue(i);
               if(s instanceof java.sql.Date){
                   return ((java.sql.Date)s);
               }
               throw new RuntimeException("tipo nao é date");
           }
           public String getStringValue(String i){
               Object s=getValue(i);
               
               if(s==null||s instanceof String){
                   return ((String)s);
               }
               throw new RuntimeException("tipo nao é string");
           }
           public boolean getBoleanValue(String i){
               Object s=getValue(i);
               if(s instanceof Boolean){
                   return ((boolean)s);
               }
               throw new RuntimeException("tipo nao é boolean");
           }
           
/*    */   public Object[][] getData() {
/* 27 */     return this.data;
/*    */   }
/*    */   public void setData(Object[][] data) {
   
/* 30 */     this.data = data;
/*    */   }
/*    */   public boolean isSucess() {
/* 33 */     return this.sucess;
/*    */   }
/*    */   public void setSucess(boolean sucess) {
/* 36 */     this.sucess = sucess;
/*    */   }
/*    */   public String getErrorMessage() {
/* 39 */     return this.errorMessage;
/*    */   }
/*    */   public void setErrorMessage(String errorMessage) {
/* 42 */     this.errorMessage = errorMessage;
/*    */   }
/*    */   public int getErrorCode() {
/* 45 */     return this.errorCode;
/*    */   }
/*    */   public void setErrorCode(int errorCode) {
/* 48 */     this.errorCode = errorCode;
/*    */   }
            public boolean next(){
               
                if(this.postAtual<this.getData().length-1){
                    if(campos==null||campos.size()==0){
                        if(campos==null) campos=new HashMap<>();
                        createRefs();
                    }
                    this.postAtual++;
                
                    return true;
                }
                else return false;
            }
/*    */ }


/* Location:              C:\HSMMGWorkSpace\AlephDataBase\lib\AlephDataBase.jar!\br\mat\mmgalvao\dbserver\commons\DataSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */