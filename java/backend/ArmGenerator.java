/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.arm;

/**
 *
 * @author DJAN DENNIS MINTAH
 */
public class ArmGenerator {
    
    DataSection dataSection;
    TextSection textSection;
    private int HEAP_SIZE=1024*4; // Heap size in bytes
    
    public ArmGenerator(){
    
        dataSection=new DataSection();
        dataSection.data.append("\t .data");
      
        
        textSection= new TextSection();
        textSection.text.append("\t .text\n");
        textSection.text.append("\t .global _start\n");
        
    }
    
    
   private void offset_operations(String mnemonic, String dest, String src, int offset){
   
        dataSection.data.append("\t").append(mnemonic).append(" ").append(dest).append("[")
                        .append(src).append(" ,#").append(offset).append(" ]\n");
   }
   
   private void move_operations(String dest, String src){
   
        dataSection.data.append("\t ").append("MOV ").append(dest).append(", ").append(src);
   
   }
   
   
   public void assign(){
   
   
   
   }
   
   
   
   
   
   
   
   
   
   
    
}
