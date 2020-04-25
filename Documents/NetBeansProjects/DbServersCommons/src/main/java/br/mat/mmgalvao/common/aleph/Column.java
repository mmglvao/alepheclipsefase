/*    */ package br.mat.mmgalvao.common.aleph;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Column implements Serializable {
/*    */   private static final long serialVersionUID = 1L;
/*    */   String name;
/*    */   int pos;
/*    */   boolean pk;
/*    */   int maxLength;
/*    */   int scale;
/*    */   int precsion;
/*    */   int nullAble;
/*    */   String typeName;
/*    */   boolean autogerado;
/*    */   
/*    */   public boolean isPk() {
/* 18 */     return this.pk;
/*    */   }
/*    */   public void setPk(boolean pk) {
/* 21 */     this.pk = pk;
/*    */   }
/*    */   
/*    */   public int getNullAble() {
/* 25 */     return this.nullAble;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 29 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 32 */     this.name = name;
/*    */   }
/*    */   public int getPos() {
/* 35 */     return this.pos;
/*    */   }
/*    */   public void setPos(int pos) {
/* 38 */     this.pos = pos;
/*    */   }
/*    */   public int getMaxLength() {
/* 41 */     return this.maxLength;
/*    */   }
/*    */   public void setMaxLength(int maxLength) {
/* 44 */     this.maxLength = maxLength;
/*    */   }
/*    */   public int getScale() {
/* 47 */     return this.scale;
/*    */   }
/*    */   public void setScale(int scale) {
/* 50 */     this.scale = scale;
/*    */   }
/*    */   public int getPrecsion() {
/* 53 */     return this.precsion;
/*    */   }
/*    */   public void setPrecsion(int precsion) {
/* 56 */     this.precsion = precsion;
/*    */   }
/*    */   public int isNullAble() {
/* 59 */     return this.nullAble;
/*    */   }
/*    */   public void setNullAble(int nullAble) {
/* 62 */     this.nullAble = nullAble;
/*    */   }
/*    */   public boolean isAutogerado() {
/* 65 */     return this.autogerado;
/*    */   }
/*    */   public void setAutogerado(boolean autogerado) {
/* 68 */     this.autogerado = autogerado;
/*    */   }
/*    */   public String getTypeName() {
/* 71 */     return this.typeName;
/*    */   }
/*    */   public void setTypeName(String typeName) {
/* 74 */     this.typeName = typeName;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 78 */     return "Column [name=" + this.name + ", pos=" + this.pos + ", pk=" + this.pk + ", maxLength=" + this.maxLength + ", scale=" + this.scale + 
/* 79 */       ", precsion=" + this.precsion + ", nullAble=" + this.nullAble + ", typeName=" + this.typeName + ", autogerado=" + 
/* 80 */       this.autogerado + "]";
/*    */   }
/*    */ }


/* Location:              C:\HSMMGWorkSpace\AlephDataBase\lib\AlephDataBase.jar!\br\mat\mmgalvao\dbserver\commons\Column.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */