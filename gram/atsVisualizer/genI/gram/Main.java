package gram;

//gram imports
import gram.i.iAdaptor;
import gram.i.types.*;

//antlr imports
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.Tree;

//tom imports
import tom.library.utils.Viewer;
import tom.library.sl.*;

//java api imports
import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.Math;

//For metrics parse file
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
  private static boolean tom_equal_term_Strategy(Object t1, Object t2) {return  (t1.equals(t2)) ;}private static boolean tom_is_sort_Strategy(Object t) {return  (t instanceof tom.library.sl.Strategy) ;} private static boolean tom_equal_term_Position(Object t1, Object t2) {return  (t1.equals(t2)) ;}private static boolean tom_is_sort_Position(Object t) {return  (t instanceof tom.library.sl.Position) ;} private static boolean tom_equal_term_int(int t1, int t2) {return  t1==t2 ;}private static boolean tom_is_sort_int(int t) {return  true ;} private static boolean tom_equal_term_char(char t1, char t2) {return  t1==t2 ;}private static boolean tom_is_sort_char(char t) {return  true ;} private static boolean tom_equal_term_String(String t1, String t2) {return  t1.equals(t2) ;}private static boolean tom_is_sort_String(String t) {return  t instanceof String ;} private static  tom.library.sl.Strategy  tom_make_mu( tom.library.sl.Strategy  var,  tom.library.sl.Strategy  v) { return ( new tom.library.sl.Mu(var,v) );}private static  tom.library.sl.Strategy  tom_make_MuVar( String  name) { return ( new tom.library.sl.MuVar(name) );}private static  tom.library.sl.Strategy  tom_make_Identity() { return ( new tom.library.sl.Identity() );}private static  tom.library.sl.Strategy  tom_make_One( tom.library.sl.Strategy  v) { return ( new tom.library.sl.One(v) );}private static  tom.library.sl.Strategy  tom_make_All( tom.library.sl.Strategy  v) { return ( new tom.library.sl.All(v) );}private static  tom.library.sl.Strategy  tom_make_Fail() { return ( new tom.library.sl.Fail() );}private static boolean tom_is_fun_sym_Sequence( tom.library.sl.Strategy  t) {return ( t instanceof tom.library.sl.Sequence );}private static  tom.library.sl.Strategy  tom_empty_list_Sequence() { return  null ;}private static  tom.library.sl.Strategy  tom_cons_list_Sequence( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { return  tom.library.sl.Sequence.make(head,tail) ;}private static  tom.library.sl.Strategy  tom_get_head_Sequence_Strategy( tom.library.sl.Strategy  t) {return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.FIRST) );}private static  tom.library.sl.Strategy  tom_get_tail_Sequence_Strategy( tom.library.sl.Strategy  t) {return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.THEN) );}private static boolean tom_is_empty_Sequence_Strategy( tom.library.sl.Strategy  t) {return ( t == null );}   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Sequence )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) ;       } else {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Sequence.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(tom_empty_list_Sequence())) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Sequence.make(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):tom_empty_list_Sequence()),end,tail)) ;   }   private static boolean tom_is_fun_sym_Choice( tom.library.sl.Strategy  t) {return ( t instanceof tom.library.sl.Choice );}private static  tom.library.sl.Strategy  tom_empty_list_Choice() { return  null ;}private static  tom.library.sl.Strategy  tom_cons_list_Choice( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { return  tom.library.sl.Choice.make(head,tail) ;}private static  tom.library.sl.Strategy  tom_get_head_Choice_Strategy( tom.library.sl.Strategy  t) {return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Choice.FIRST) );}private static  tom.library.sl.Strategy  tom_get_tail_Choice_Strategy( tom.library.sl.Strategy  t) {return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Choice.THEN) );}private static boolean tom_is_empty_Choice_Strategy( tom.library.sl.Strategy  t) {return ( t ==null );}   private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Choice )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) ;       } else {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Choice.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(tom_empty_list_Choice())) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Choice.make(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ):tom_empty_list_Choice()),end,tail)) ;   }   private static boolean tom_is_fun_sym_SequenceId( tom.library.sl.Strategy  t) {return ( t instanceof tom.library.sl.SequenceId );}private static  tom.library.sl.Strategy  tom_empty_list_SequenceId() { return  null ;}private static  tom.library.sl.Strategy  tom_cons_list_SequenceId( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { return  tom.library.sl.SequenceId.make(head,tail) ;}private static  tom.library.sl.Strategy  tom_get_head_SequenceId_Strategy( tom.library.sl.Strategy  t) {return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.SequenceId.FIRST) );}private static  tom.library.sl.Strategy  tom_get_tail_SequenceId_Strategy( tom.library.sl.Strategy  t) {return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.SequenceId.THEN) );}private static boolean tom_is_empty_SequenceId_Strategy( tom.library.sl.Strategy  t) {return ( t == null );}   private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.SequenceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.SequenceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(tom_empty_list_SequenceId())) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.SequenceId.make(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ):tom_empty_list_SequenceId()),end,tail)) ;   }   private static boolean tom_is_fun_sym_ChoiceId( tom.library.sl.Strategy  t) {return ( t instanceof tom.library.sl.ChoiceId );}private static  tom.library.sl.Strategy  tom_empty_list_ChoiceId() { return  null ;}private static  tom.library.sl.Strategy  tom_cons_list_ChoiceId( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { return  tom.library.sl.ChoiceId.make(head,tail) ;}private static  tom.library.sl.Strategy  tom_get_head_ChoiceId_Strategy( tom.library.sl.Strategy  t) {return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.ChoiceId.FIRST) );}private static  tom.library.sl.Strategy  tom_get_tail_ChoiceId_Strategy( tom.library.sl.Strategy  t) {return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.ChoiceId.THEN) );}private static boolean tom_is_empty_ChoiceId_Strategy( tom.library.sl.Strategy  t) {return ( t ==null );}   private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.ChoiceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.ChoiceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(tom_empty_list_ChoiceId())) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.ChoiceId.make(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ):tom_empty_list_ChoiceId()),end,tail)) ;   }   private static  tom.library.sl.Strategy  tom_make_OneId( tom.library.sl.Strategy  v) { return ( new tom.library.sl.OneId(v) );}   private static  tom.library.sl.Strategy  tom_make_AllSeq( tom.library.sl.Strategy  s) { return ( new tom.library.sl.AllSeq(s) );}private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( tom_make_mu(tom_make_MuVar("x"),tom_cons_list_Choice(s2,tom_cons_list_Choice(tom_cons_list_Sequence(tom_cons_list_Sequence(s1,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("x")),tom_empty_list_Sequence())),tom_cons_list_Sequence(tom_make_One(tom_make_Identity()),tom_empty_list_Sequence())),tom_empty_list_Choice()))) );}private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( tom_make_mu(tom_make_MuVar("x"),tom_cons_list_Choice(s2,tom_cons_list_Choice(tom_cons_list_Sequence(s1,tom_cons_list_Sequence(tom_make_One(tom_make_MuVar("x")),tom_empty_list_Sequence())),tom_empty_list_Choice()))));} private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { return ( tom_cons_list_Choice(s,tom_cons_list_Choice(tom_make_Identity(),tom_empty_list_Choice())) );}private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { return ( tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Choice(tom_cons_list_Sequence(s,tom_cons_list_Sequence(tom_make_MuVar("_x"),tom_empty_list_Sequence())),tom_cons_list_Choice(tom_make_Identity(),tom_empty_list_Choice()))) );}private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Sequence(v,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_empty_list_Sequence()))) );}private static  tom.library.sl.Strategy  tom_make_BottomUp( tom.library.sl.Strategy  v) { return ( tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_cons_list_Sequence(v,tom_empty_list_Sequence()))) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { return ( tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Choice(v,tom_cons_list_Choice(tom_make_One(tom_make_MuVar("_x")),tom_empty_list_Choice()))) );}private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { return ( tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_SequenceId(v,tom_cons_list_SequenceId(tom_make_MuVar("_x"),tom_empty_list_SequenceId()))) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { return ( tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_ChoiceId(v,tom_cons_list_ChoiceId(tom_make_OneId(tom_make_MuVar("_x")),tom_empty_list_ChoiceId()))) );}      static class MapEntry {   private Object key;   private Object val;   public Object getKey() { return key; }   public Object getVal() { return val; }   public MapEntry(Object key, Object val) {     this.key = key;     this.val = val;   } }  private static boolean tom_equal_term_MapEntry(Object e1, Object e2) {return  e1.equals(e2) ;}private static boolean tom_is_sort_MapEntry(Object t) {return  t instanceof MapEntry ;}private static boolean tom_equal_term_Object(Object o1, Object o2) {return  o1.equals(o2) ;}private static boolean tom_is_sort_Object(Object t) {return  t instanceof Object ;}  private static boolean tom_equal_term_HashMap(Object l1, Object l2) {return  l1.equals(l2) ;}private static boolean tom_is_sort_HashMap(Object t) {return  t instanceof java.util.HashMap ;}   @SuppressWarnings("unchecked") private static java.util.HashMap hashMapAppend(MapEntry e, java.util.HashMap m) {   java.util.HashMap res = (java.util.HashMap) m.clone();   res.put(e.getKey(), e.getVal());   return res; }  @SuppressWarnings("unchecked") private static MapEntry hashMapGetHead(java.util.HashMap m) {   java.util.Set es = m.entrySet();   java.util.Iterator it = es.iterator();   java.util.Map.Entry e = (java.util.Map.Entry) it.next();   return new MapEntry(e.getKey(), e.getValue()); }  @SuppressWarnings("unchecked") private static java.util.HashMap hashMapGetTail(java.util.HashMap m) {   java.util.HashMap res = (java.util.HashMap) m.clone();   java.util.Set es = m.entrySet();   java.util.Iterator it = es.iterator();   java.util.Map.Entry e = (java.util.Map.Entry) it.next();   res.remove(e.getKey());   return res; }   private static boolean tom_equal_term_ArrayList(Object l1, Object l2) {return  l1.equals(l2) ;}private static boolean tom_is_sort_ArrayList(Object t) {return  t instanceof java.util.ArrayList ;}   @SuppressWarnings("unchecked") private static java.util.ArrayList concArrayListAppend(Object o, java.util.ArrayList l) {   java.util.ArrayList res = (java.util.ArrayList)l.clone();   res.add(o);   return res; } private static boolean tom_equal_term_Collection(Object l1, Object l2) {return  l1.equals(l2) ;}private static boolean tom_is_sort_Collection(Object t) {return  t instanceof java.util.Collection ;} private static boolean tom_equal_term_Set(Object l1, Object l2) {return  l1.equals(l2) ;}private static boolean tom_is_sort_Set(Object t) {return  t instanceof java.util.Set ;} private static boolean tom_equal_term_DefTipo(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_DefTipo(Object t) {return  (t instanceof gram.i.types.DefTipo) ;}private static boolean tom_equal_term_OpNum(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_OpNum(Object t) {return  (t instanceof gram.i.types.OpNum) ;}private static boolean tom_equal_term_LComentarios(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_LComentarios(Object t) {return  (t instanceof gram.i.types.LComentarios) ;}private static boolean tom_equal_term_OpComp(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_OpComp(Object t) {return  (t instanceof gram.i.types.OpComp) ;}private static boolean tom_equal_term_Expressao(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Expressao(Object t) {return  (t instanceof gram.i.types.Expressao) ;}private static boolean tom_equal_term_OpAtribuicao(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_OpAtribuicao(Object t) {return  (t instanceof gram.i.types.OpAtribuicao) ;}private static boolean tom_equal_term_OpInc(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_OpInc(Object t) {return  (t instanceof gram.i.types.OpInc) ;}private static boolean tom_equal_term_Parametros(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Parametros(Object t) {return  (t instanceof gram.i.types.Parametros) ;}private static boolean tom_equal_term_Argumentos(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Argumentos(Object t) {return  (t instanceof gram.i.types.Argumentos) ;}private static boolean tom_equal_term_Declaracoes(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Declaracoes(Object t) {return  (t instanceof gram.i.types.Declaracoes) ;}private static boolean tom_equal_term_Instrucao(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Instrucao(Object t) {return  (t instanceof gram.i.types.Instrucao) ;}private static boolean tom_equal_term_IntWrapper(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_IntWrapper(Object t) {return  (t instanceof gram.i.types.IntWrapper) ;}private static boolean tom_is_fun_sym_DInt( gram.i.types.DefTipo  t) {return  (t instanceof gram.i.types.deftipo.DInt) ;}private static boolean tom_is_fun_sym_DChar( gram.i.types.DefTipo  t) {return  (t instanceof gram.i.types.deftipo.DChar) ;}private static boolean tom_is_fun_sym_DBoolean( gram.i.types.DefTipo  t) {return  (t instanceof gram.i.types.deftipo.DBoolean) ;}private static boolean tom_is_fun_sym_DFloat( gram.i.types.DefTipo  t) {return  (t instanceof gram.i.types.deftipo.DFloat) ;}private static boolean tom_is_fun_sym_DVoid( gram.i.types.DefTipo  t) {return  (t instanceof gram.i.types.deftipo.DVoid) ;}private static boolean tom_is_fun_sym_Mais( gram.i.types.OpNum  t) {return  (t instanceof gram.i.types.opnum.Mais) ;}private static boolean tom_is_fun_sym_Vezes( gram.i.types.OpNum  t) {return  (t instanceof gram.i.types.opnum.Vezes) ;}private static boolean tom_is_fun_sym_Divide( gram.i.types.OpNum  t) {return  (t instanceof gram.i.types.opnum.Divide) ;}private static boolean tom_is_fun_sym_Menos( gram.i.types.OpNum  t) {return  (t instanceof gram.i.types.opnum.Menos) ;}private static boolean tom_is_fun_sym_Mod( gram.i.types.OpNum  t) {return  (t instanceof gram.i.types.opnum.Mod) ;}private static boolean tom_is_fun_sym_Comentario( gram.i.types.LComentarios  t) {return  (t instanceof gram.i.types.lcomentarios.Comentario) ;}private static  String  tom_get_slot_Comentario_comentario( gram.i.types.LComentarios  t) {return  t.getcomentario() ;}private static  gram.i.types.LComentarios  tom_make_Vazio() { return  gram.i.types.lcomentarios.Vazio.make() ;}private static boolean tom_is_fun_sym_Maior( gram.i.types.OpComp  t) {return  (t instanceof gram.i.types.opcomp.Maior) ;}private static boolean tom_is_fun_sym_Menor( gram.i.types.OpComp  t) {return  (t instanceof gram.i.types.opcomp.Menor) ;}private static boolean tom_is_fun_sym_MaiorQ( gram.i.types.OpComp  t) {return  (t instanceof gram.i.types.opcomp.MaiorQ) ;}private static boolean tom_is_fun_sym_MenorQ( gram.i.types.OpComp  t) {return  (t instanceof gram.i.types.opcomp.MenorQ) ;}private static boolean tom_is_fun_sym_Dif( gram.i.types.OpComp  t) {return  (t instanceof gram.i.types.opcomp.Dif) ;}private static boolean tom_is_fun_sym_Igual( gram.i.types.OpComp  t) {return  (t instanceof gram.i.types.opcomp.Igual) ;}private static boolean tom_is_fun_sym_ExpNum( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.ExpNum) ;}private static  gram.i.types.Expressao  tom_get_slot_ExpNum_Exp1( gram.i.types.Expressao  t) {return  t.getExp1() ;}private static  gram.i.types.LComentarios  tom_get_slot_ExpNum_c1( gram.i.types.Expressao  t) {return  t.getc1() ;}private static  gram.i.types.OpNum  tom_get_slot_ExpNum_op( gram.i.types.Expressao  t) {return  t.getop() ;}private static  gram.i.types.LComentarios  tom_get_slot_ExpNum_c2( gram.i.types.Expressao  t) {return  t.getc2() ;}private static  gram.i.types.Expressao  tom_get_slot_ExpNum_Exp2( gram.i.types.Expressao  t) {return  t.getExp2() ;}private static boolean tom_is_fun_sym_Id( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Id) ;}private static  String  tom_get_slot_Id_Id( gram.i.types.Expressao  t) {return  t.getId() ;}private static boolean tom_is_fun_sym_Pos( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Pos) ;}private static  gram.i.types.Expressao  tom_get_slot_Pos_Expressao( gram.i.types.Expressao  t) {return  t.getExpressao() ;}private static boolean tom_is_fun_sym_Neg( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Neg) ;}private static  gram.i.types.Expressao  tom_get_slot_Neg_Expressao( gram.i.types.Expressao  t) {return  t.getExpressao() ;}private static boolean tom_is_fun_sym_Nao( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Nao) ;}private static  gram.i.types.Expressao  tom_make_Nao( gram.i.types.Expressao  t0) { return  gram.i.types.expressao.Nao.make(t0) ;}private static  gram.i.types.Expressao  tom_get_slot_Nao_Expressao( gram.i.types.Expressao  t) {return  t.getExpressao() ;}private static boolean tom_is_fun_sym_Call( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Call) ;}private static  gram.i.types.LComentarios  tom_get_slot_Call_c1( gram.i.types.Expressao  t) {return  t.getc1() ;}private static  String  tom_get_slot_Call_Id( gram.i.types.Expressao  t) {return  t.getId() ;}private static  gram.i.types.LComentarios  tom_get_slot_Call_c2( gram.i.types.Expressao  t) {return  t.getc2() ;}private static  gram.i.types.LComentarios  tom_get_slot_Call_c3( gram.i.types.Expressao  t) {return  t.getc3() ;}private static  gram.i.types.Parametros  tom_get_slot_Call_Parametros( gram.i.types.Expressao  t) {return  t.getParametros() ;}private static  gram.i.types.LComentarios  tom_get_slot_Call_c4( gram.i.types.Expressao  t) {return  t.getc4() ;}private static  gram.i.types.LComentarios  tom_get_slot_Call_c5( gram.i.types.Expressao  t) {return  t.getc5() ;}private static boolean tom_is_fun_sym_IncAntes( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.IncAntes) ;}private static  gram.i.types.OpInc  tom_get_slot_IncAntes_OpInc( gram.i.types.Expressao  t) {return  t.getOpInc() ;}private static  String  tom_get_slot_IncAntes_Id( gram.i.types.Expressao  t) {return  t.getId() ;}private static boolean tom_is_fun_sym_IncDepois( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.IncDepois) ;}private static  gram.i.types.OpInc  tom_get_slot_IncDepois_OpInc( gram.i.types.Expressao  t) {return  t.getOpInc() ;}private static  String  tom_get_slot_IncDepois_Id( gram.i.types.Expressao  t) {return  t.getId() ;}private static boolean tom_is_fun_sym_Condicional( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Condicional) ;}private static  gram.i.types.Expressao  tom_get_slot_Condicional_Condicao( gram.i.types.Expressao  t) {return  t.getCondicao() ;}private static  gram.i.types.LComentarios  tom_get_slot_Condicional_c1( gram.i.types.Expressao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Condicional_c2( gram.i.types.Expressao  t) {return  t.getc2() ;}private static  gram.i.types.Expressao  tom_get_slot_Condicional_Exp1( gram.i.types.Expressao  t) {return  t.getExp1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Condicional_c3( gram.i.types.Expressao  t) {return  t.getc3() ;}private static  gram.i.types.LComentarios  tom_get_slot_Condicional_c4( gram.i.types.Expressao  t) {return  t.getc4() ;}private static  gram.i.types.Expressao  tom_get_slot_Condicional_Exp2( gram.i.types.Expressao  t) {return  t.getExp2() ;}private static boolean tom_is_fun_sym_Int( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Int) ;}private static  gram.i.types.Expressao  tom_make_Int( int  t0) { return  gram.i.types.expressao.Int.make(t0) ;}private static  int  tom_get_slot_Int_Int( gram.i.types.Expressao  t) {return  t.getInt() ;}private static boolean tom_is_fun_sym_Char( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Char) ;}private static  gram.i.types.Expressao  tom_make_Char( String  t0) { return  gram.i.types.expressao.Char.make(t0) ;}private static  String  tom_get_slot_Char_Char( gram.i.types.Expressao  t) {return  t.getChar() ;}private static boolean tom_is_fun_sym_True( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.True) ;}private static boolean tom_is_fun_sym_False( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.False) ;}private static boolean tom_is_fun_sym_Float( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Float) ;}private static  int  tom_get_slot_Float_num( gram.i.types.Expressao  t) {return  t.getnum() ;}private static boolean tom_is_fun_sym_Ou( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Ou) ;}private static  gram.i.types.Expressao  tom_get_slot_Ou_Cond1( gram.i.types.Expressao  t) {return  t.getCond1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Ou_c1( gram.i.types.Expressao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Ou_c2( gram.i.types.Expressao  t) {return  t.getc2() ;}private static  gram.i.types.Expressao  tom_get_slot_Ou_Cond2( gram.i.types.Expressao  t) {return  t.getCond2() ;}private static boolean tom_is_fun_sym_E( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.E) ;}private static  gram.i.types.Expressao  tom_get_slot_E_Cond1( gram.i.types.Expressao  t) {return  t.getCond1() ;}private static  gram.i.types.LComentarios  tom_get_slot_E_c1( gram.i.types.Expressao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_E_c2( gram.i.types.Expressao  t) {return  t.getc2() ;}private static  gram.i.types.Expressao  tom_get_slot_E_Cond2( gram.i.types.Expressao  t) {return  t.getCond2() ;}private static boolean tom_is_fun_sym_Comp( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Comp) ;}private static  gram.i.types.Expressao  tom_get_slot_Comp_Exp1( gram.i.types.Expressao  t) {return  t.getExp1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Comp_c1( gram.i.types.Expressao  t) {return  t.getc1() ;}private static  gram.i.types.OpComp  tom_get_slot_Comp_OpComp( gram.i.types.Expressao  t) {return  t.getOpComp() ;}private static  gram.i.types.LComentarios  tom_get_slot_Comp_c2( gram.i.types.Expressao  t) {return  t.getc2() ;}private static  gram.i.types.Expressao  tom_get_slot_Comp_Exp2( gram.i.types.Expressao  t) {return  t.getExp2() ;}private static boolean tom_is_fun_sym_Input( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Input) ;}private static  gram.i.types.LComentarios  tom_get_slot_Input_c1( gram.i.types.Expressao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Input_c2( gram.i.types.Expressao  t) {return  t.getc2() ;}private static  gram.i.types.LComentarios  tom_get_slot_Input_c3( gram.i.types.Expressao  t) {return  t.getc3() ;}private static  gram.i.types.DefTipo  tom_get_slot_Input_Tipo( gram.i.types.Expressao  t) {return  t.getTipo() ;}private static  gram.i.types.LComentarios  tom_get_slot_Input_c4( gram.i.types.Expressao  t) {return  t.getc4() ;}private static  gram.i.types.LComentarios  tom_get_slot_Input_c5( gram.i.types.Expressao  t) {return  t.getc5() ;}private static boolean tom_is_fun_sym_Print( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Print) ;}private static  gram.i.types.Expressao  tom_make_Print( gram.i.types.LComentarios  t0,  gram.i.types.LComentarios  t1,  gram.i.types.LComentarios  t2,  gram.i.types.Expressao  t3,  gram.i.types.LComentarios  t4,  gram.i.types.LComentarios  t5) { return  gram.i.types.expressao.Print.make(t0, t1, t2, t3, t4, t5) ;}private static  gram.i.types.LComentarios  tom_get_slot_Print_c1( gram.i.types.Expressao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Print_c2( gram.i.types.Expressao  t) {return  t.getc2() ;}private static  gram.i.types.LComentarios  tom_get_slot_Print_c3( gram.i.types.Expressao  t) {return  t.getc3() ;}private static  gram.i.types.Expressao  tom_get_slot_Print_Expressao( gram.i.types.Expressao  t) {return  t.getExpressao() ;}private static  gram.i.types.LComentarios  tom_get_slot_Print_c4( gram.i.types.Expressao  t) {return  t.getc4() ;}private static  gram.i.types.LComentarios  tom_get_slot_Print_c5( gram.i.types.Expressao  t) {return  t.getc5() ;}private static boolean tom_is_fun_sym_Empty( gram.i.types.Expressao  t) {return  (t instanceof gram.i.types.expressao.Empty) ;}private static  gram.i.types.Expressao  tom_make_Empty() { return  gram.i.types.expressao.Empty.make() ;}private static boolean tom_is_fun_sym_Atrib( gram.i.types.OpAtribuicao  t) {return  (t instanceof gram.i.types.opatribuicao.Atrib) ;}private static boolean tom_is_fun_sym_Mult( gram.i.types.OpAtribuicao  t) {return  (t instanceof gram.i.types.opatribuicao.Mult) ;}private static boolean tom_is_fun_sym_Div( gram.i.types.OpAtribuicao  t) {return  (t instanceof gram.i.types.opatribuicao.Div) ;}private static boolean tom_is_fun_sym_Soma( gram.i.types.OpAtribuicao  t) {return  (t instanceof gram.i.types.opatribuicao.Soma) ;}private static boolean tom_is_fun_sym_Sub( gram.i.types.OpAtribuicao  t) {return  (t instanceof gram.i.types.opatribuicao.Sub) ;}private static boolean tom_is_fun_sym_Inc( gram.i.types.OpInc  t) {return  (t instanceof gram.i.types.opinc.Inc) ;}private static boolean tom_is_fun_sym_Dec( gram.i.types.OpInc  t) {return  (t instanceof gram.i.types.opinc.Dec) ;}private static boolean tom_is_fun_sym_Parametro( gram.i.types.Parametros  t) {return  (t instanceof gram.i.types.parametros.Parametro) ;}private static  gram.i.types.LComentarios  tom_get_slot_Parametro_c1( gram.i.types.Parametros  t) {return  t.getc1() ;}private static  gram.i.types.Expressao  tom_get_slot_Parametro_Expressao( gram.i.types.Parametros  t) {return  t.getExpressao() ;}private static  gram.i.types.LComentarios  tom_get_slot_Parametro_c2( gram.i.types.Parametros  t) {return  t.getc2() ;}private static boolean tom_is_fun_sym_Argumento( gram.i.types.Argumentos  t) {return  (t instanceof gram.i.types.argumentos.Argumento) ;}private static  gram.i.types.LComentarios  tom_get_slot_Argumento_c1( gram.i.types.Argumentos  t) {return  t.getc1() ;}private static  gram.i.types.DefTipo  tom_get_slot_Argumento_DefTipo( gram.i.types.Argumentos  t) {return  t.getDefTipo() ;}private static  gram.i.types.LComentarios  tom_get_slot_Argumento_c2( gram.i.types.Argumentos  t) {return  t.getc2() ;}private static  String  tom_get_slot_Argumento_Id( gram.i.types.Argumentos  t) {return  t.getId() ;}private static  gram.i.types.LComentarios  tom_get_slot_Argumento_c3( gram.i.types.Argumentos  t) {return  t.getc3() ;}private static boolean tom_is_fun_sym_Decl( gram.i.types.Declaracoes  t) {return  (t instanceof gram.i.types.declaracoes.Decl) ;}private static  String  tom_get_slot_Decl_Id( gram.i.types.Declaracoes  t) {return  t.getId() ;}private static  gram.i.types.LComentarios  tom_get_slot_Decl_c1( gram.i.types.Declaracoes  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Decl_c2( gram.i.types.Declaracoes  t) {return  t.getc2() ;}private static  gram.i.types.Expressao  tom_get_slot_Decl_Expressao( gram.i.types.Declaracoes  t) {return  t.getExpressao() ;}private static  gram.i.types.LComentarios  tom_get_slot_Decl_c3( gram.i.types.Declaracoes  t) {return  t.getc3() ;}private static boolean tom_is_fun_sym_Atribuicao( gram.i.types.Instrucao  t) {return  (t instanceof gram.i.types.instrucao.Atribuicao) ;}private static  gram.i.types.LComentarios  tom_get_slot_Atribuicao_c1( gram.i.types.Instrucao  t) {return  t.getc1() ;}private static  String  tom_get_slot_Atribuicao_Id( gram.i.types.Instrucao  t) {return  t.getId() ;}private static  gram.i.types.LComentarios  tom_get_slot_Atribuicao_c2( gram.i.types.Instrucao  t) {return  t.getc2() ;}private static  gram.i.types.OpAtribuicao  tom_get_slot_Atribuicao_op( gram.i.types.Instrucao  t) {return  t.getop() ;}private static  gram.i.types.LComentarios  tom_get_slot_Atribuicao_c3( gram.i.types.Instrucao  t) {return  t.getc3() ;}private static  gram.i.types.Expressao  tom_get_slot_Atribuicao_Expressao( gram.i.types.Instrucao  t) {return  t.getExpressao() ;}private static  gram.i.types.LComentarios  tom_get_slot_Atribuicao_c4( gram.i.types.Instrucao  t) {return  t.getc4() ;}private static boolean tom_is_fun_sym_Declaracao( gram.i.types.Instrucao  t) {return  (t instanceof gram.i.types.instrucao.Declaracao) ;}private static  gram.i.types.LComentarios  tom_get_slot_Declaracao_c1( gram.i.types.Instrucao  t) {return  t.getc1() ;}private static  gram.i.types.DefTipo  tom_get_slot_Declaracao_DefTipo( gram.i.types.Instrucao  t) {return  t.getDefTipo() ;}private static  gram.i.types.LComentarios  tom_get_slot_Declaracao_c2( gram.i.types.Instrucao  t) {return  t.getc2() ;}private static  gram.i.types.Declaracoes  tom_get_slot_Declaracao_Declaracoes( gram.i.types.Instrucao  t) {return  t.getDeclaracoes() ;}private static  gram.i.types.LComentarios  tom_get_slot_Declaracao_c3( gram.i.types.Instrucao  t) {return  t.getc3() ;}private static  gram.i.types.LComentarios  tom_get_slot_Declaracao_c4( gram.i.types.Instrucao  t) {return  t.getc4() ;}private static boolean tom_is_fun_sym_If( gram.i.types.Instrucao  t) {return  (t instanceof gram.i.types.instrucao.If) ;}private static  gram.i.types.Instrucao  tom_make_If( gram.i.types.LComentarios  t0,  gram.i.types.LComentarios  t1,  gram.i.types.LComentarios  t2,  gram.i.types.Expressao  t3,  gram.i.types.LComentarios  t4,  gram.i.types.LComentarios  t5,  gram.i.types.Instrucao  t6,  gram.i.types.Instrucao  t7) { return  gram.i.types.instrucao.If.make(t0, t1, t2, t3, t4, t5, t6, t7) ;}private static  gram.i.types.LComentarios  tom_get_slot_If_c1( gram.i.types.Instrucao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_If_c2( gram.i.types.Instrucao  t) {return  t.getc2() ;}private static  gram.i.types.LComentarios  tom_get_slot_If_c3( gram.i.types.Instrucao  t) {return  t.getc3() ;}private static  gram.i.types.Expressao  tom_get_slot_If_Condicao( gram.i.types.Instrucao  t) {return  t.getCondicao() ;}private static  gram.i.types.LComentarios  tom_get_slot_If_c4( gram.i.types.Instrucao  t) {return  t.getc4() ;}private static  gram.i.types.LComentarios  tom_get_slot_If_c5( gram.i.types.Instrucao  t) {return  t.getc5() ;}private static  gram.i.types.Instrucao  tom_get_slot_If_Instrucao1( gram.i.types.Instrucao  t) {return  t.getInstrucao1() ;}private static  gram.i.types.Instrucao  tom_get_slot_If_Instrucao2( gram.i.types.Instrucao  t) {return  t.getInstrucao2() ;}private static boolean tom_is_fun_sym_While( gram.i.types.Instrucao  t) {return  (t instanceof gram.i.types.instrucao.While) ;}private static  gram.i.types.Instrucao  tom_make_While( gram.i.types.LComentarios  t0,  gram.i.types.LComentarios  t1,  gram.i.types.LComentarios  t2,  gram.i.types.Expressao  t3,  gram.i.types.LComentarios  t4,  gram.i.types.LComentarios  t5,  gram.i.types.Instrucao  t6,  gram.i.types.LComentarios  t7) { return  gram.i.types.instrucao.While.make(t0, t1, t2, t3, t4, t5, t6, t7) ;}private static  gram.i.types.LComentarios  tom_get_slot_While_c1( gram.i.types.Instrucao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_While_c2( gram.i.types.Instrucao  t) {return  t.getc2() ;}private static  gram.i.types.LComentarios  tom_get_slot_While_c3( gram.i.types.Instrucao  t) {return  t.getc3() ;}private static  gram.i.types.Expressao  tom_get_slot_While_Condicao( gram.i.types.Instrucao  t) {return  t.getCondicao() ;}private static  gram.i.types.LComentarios  tom_get_slot_While_c4( gram.i.types.Instrucao  t) {return  t.getc4() ;}private static  gram.i.types.LComentarios  tom_get_slot_While_c5( gram.i.types.Instrucao  t) {return  t.getc5() ;}private static  gram.i.types.Instrucao  tom_get_slot_While_Instrucao( gram.i.types.Instrucao  t) {return  t.getInstrucao() ;}private static  gram.i.types.LComentarios  tom_get_slot_While_c6( gram.i.types.Instrucao  t) {return  t.getc6() ;}private static boolean tom_is_fun_sym_For( gram.i.types.Instrucao  t) {return  (t instanceof gram.i.types.instrucao.For) ;}private static  gram.i.types.Instrucao  tom_make_For( gram.i.types.LComentarios  t0,  gram.i.types.LComentarios  t1,  gram.i.types.Instrucao  t2,  gram.i.types.LComentarios  t3,  gram.i.types.Expressao  t4,  gram.i.types.LComentarios  t5,  gram.i.types.LComentarios  t6,  gram.i.types.Expressao  t7,  gram.i.types.LComentarios  t8,  gram.i.types.LComentarios  t9,  gram.i.types.Instrucao  t10,  gram.i.types.LComentarios  t11) { return  gram.i.types.instrucao.For.make(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11) ;}private static  gram.i.types.LComentarios  tom_get_slot_For_c1( gram.i.types.Instrucao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_For_c2( gram.i.types.Instrucao  t) {return  t.getc2() ;}private static  gram.i.types.Instrucao  tom_get_slot_For_Declaracao( gram.i.types.Instrucao  t) {return  t.getDeclaracao() ;}private static  gram.i.types.LComentarios  tom_get_slot_For_c3( gram.i.types.Instrucao  t) {return  t.getc3() ;}private static  gram.i.types.Expressao  tom_get_slot_For_Condicao( gram.i.types.Instrucao  t) {return  t.getCondicao() ;}private static  gram.i.types.LComentarios  tom_get_slot_For_c4( gram.i.types.Instrucao  t) {return  t.getc4() ;}private static  gram.i.types.LComentarios  tom_get_slot_For_c5( gram.i.types.Instrucao  t) {return  t.getc5() ;}private static  gram.i.types.Expressao  tom_get_slot_For_Expressao( gram.i.types.Instrucao  t) {return  t.getExpressao() ;}private static  gram.i.types.LComentarios  tom_get_slot_For_c6( gram.i.types.Instrucao  t) {return  t.getc6() ;}private static  gram.i.types.LComentarios  tom_get_slot_For_c7( gram.i.types.Instrucao  t) {return  t.getc7() ;}private static  gram.i.types.Instrucao  tom_get_slot_For_Instrucao( gram.i.types.Instrucao  t) {return  t.getInstrucao() ;}private static  gram.i.types.LComentarios  tom_get_slot_For_c8( gram.i.types.Instrucao  t) {return  t.getc8() ;}private static boolean tom_is_fun_sym_Return( gram.i.types.Instrucao  t) {return  (t instanceof gram.i.types.instrucao.Return) ;}private static  gram.i.types.LComentarios  tom_get_slot_Return_c1( gram.i.types.Instrucao  t) {return  t.getc1() ;}private static  gram.i.types.LComentarios  tom_get_slot_Return_c2( gram.i.types.Instrucao  t) {return  t.getc2() ;}private static  gram.i.types.Expressao  tom_get_slot_Return_Expressao( gram.i.types.Instrucao  t) {return  t.getExpressao() ;}private static  gram.i.types.LComentarios  tom_get_slot_Return_c3( gram.i.types.Instrucao  t) {return  t.getc3() ;}private static boolean tom_is_fun_sym_Funcao( gram.i.types.Instrucao  t) {return  (t instanceof gram.i.types.instrucao.Funcao) ;}private static  gram.i.types.Instrucao  tom_make_Funcao( gram.i.types.LComentarios  t0,  gram.i.types.DefTipo  t1,  gram.i.types.LComentarios  t2,  String  t3,  gram.i.types.LComentarios  t4,  gram.i.types.LComentarios  t5,  gram.i.types.Argumentos  t6,  gram.i.types.LComentarios  t7,  gram.i.types.LComentarios  t8,  gram.i.types.Instrucao  t9,  gram.i.types.LComentarios  t10) { return  gram.i.types.instrucao.Funcao.make(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) ;}private static  gram.i.types.LComentarios  tom_get_slot_Funcao_c1( gram.i.types.Instrucao  t) {return  t.getc1() ;}private static  gram.i.types.DefTipo  tom_get_slot_Funcao_DefTipo( gram.i.types.Instrucao  t) {return  t.getDefTipo() ;}private static  gram.i.types.LComentarios  tom_get_slot_Funcao_c2( gram.i.types.Instrucao  t) {return  t.getc2() ;}private static  String  tom_get_slot_Funcao_Nome( gram.i.types.Instrucao  t) {return  t.getNome() ;}private static  gram.i.types.LComentarios  tom_get_slot_Funcao_c3( gram.i.types.Instrucao  t) {return  t.getc3() ;}private static  gram.i.types.LComentarios  tom_get_slot_Funcao_c4( gram.i.types.Instrucao  t) {return  t.getc4() ;}private static  gram.i.types.Argumentos  tom_get_slot_Funcao_Argumentos( gram.i.types.Instrucao  t) {return  t.getArgumentos() ;}private static  gram.i.types.LComentarios  tom_get_slot_Funcao_c5( gram.i.types.Instrucao  t) {return  t.getc5() ;}private static  gram.i.types.LComentarios  tom_get_slot_Funcao_c6( gram.i.types.Instrucao  t) {return  t.getc6() ;}private static  gram.i.types.Instrucao  tom_get_slot_Funcao_Instrucao( gram.i.types.Instrucao  t) {return  t.getInstrucao() ;}private static  gram.i.types.LComentarios  tom_get_slot_Funcao_c7( gram.i.types.Instrucao  t) {return  t.getc7() ;}private static boolean tom_is_fun_sym_Exp( gram.i.types.Instrucao  t) {return  (t instanceof gram.i.types.instrucao.Exp) ;}private static  gram.i.types.Instrucao  tom_make_Exp( gram.i.types.Expressao  t0) { return  gram.i.types.instrucao.Exp.make(t0) ;}private static  gram.i.types.Expressao  tom_get_slot_Exp_Expressao( gram.i.types.Instrucao  t) {return  t.getExpressao() ;}private static boolean tom_is_fun_sym_Expressoes( gram.i.types.Expressao  t) {return  ((t instanceof gram.i.types.expressao.ConsExpressoes) || (t instanceof gram.i.types.expressao.EmptyExpressoes)) ;}private static  gram.i.types.Expressao  tom_empty_list_Expressoes() { return  gram.i.types.expressao.EmptyExpressoes.make() ;}private static  gram.i.types.Expressao  tom_cons_list_Expressoes( gram.i.types.Expressao  e,  gram.i.types.Expressao  l) { return  gram.i.types.expressao.ConsExpressoes.make(e,l) ;}private static  gram.i.types.Expressao  tom_get_head_Expressoes_Expressao( gram.i.types.Expressao  l) {return  l.getHeadExpressoes() ;}private static  gram.i.types.Expressao  tom_get_tail_Expressoes_Expressao( gram.i.types.Expressao  l) {return  l.getTailExpressoes() ;}private static boolean tom_is_empty_Expressoes_Expressao( gram.i.types.Expressao  l) {return  l.isEmptyExpressoes() ;}   private static   gram.i.types.Expressao  tom_append_list_Expressoes( gram.i.types.Expressao  l1,  gram.i.types.Expressao  l2) {     if( l1.isEmptyExpressoes() ) {       return l2;     } else if( l2.isEmptyExpressoes() ) {       return l1;     } else if( ((l1 instanceof gram.i.types.expressao.ConsExpressoes) || (l1 instanceof gram.i.types.expressao.EmptyExpressoes)) ) {       if(  l1.getTailExpressoes() .isEmptyExpressoes() ) {         return  gram.i.types.expressao.ConsExpressoes.make( l1.getHeadExpressoes() ,l2) ;       } else {         return  gram.i.types.expressao.ConsExpressoes.make( l1.getHeadExpressoes() ,tom_append_list_Expressoes( l1.getTailExpressoes() ,l2)) ;       }     } else {       return  gram.i.types.expressao.ConsExpressoes.make(l1,l2) ;     }   }   private static   gram.i.types.Expressao  tom_get_slice_Expressoes( gram.i.types.Expressao  begin,  gram.i.types.Expressao  end, gram.i.types.Expressao  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyExpressoes()  ||  (end==tom_empty_list_Expressoes()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  gram.i.types.expressao.ConsExpressoes.make((( ((begin instanceof gram.i.types.expressao.ConsExpressoes) || (begin instanceof gram.i.types.expressao.EmptyExpressoes)) )? begin.getHeadExpressoes() :begin),( gram.i.types.Expressao )tom_get_slice_Expressoes((( ((begin instanceof gram.i.types.expressao.ConsExpressoes) || (begin instanceof gram.i.types.expressao.EmptyExpressoes)) )? begin.getTailExpressoes() :tom_empty_list_Expressoes()),end,tail)) ;   }   private static boolean tom_is_fun_sym_ListaParametros( gram.i.types.Parametros  t) {return  ((t instanceof gram.i.types.parametros.ConsListaParametros) || (t instanceof gram.i.types.parametros.EmptyListaParametros)) ;}private static  gram.i.types.Parametros  tom_empty_list_ListaParametros() { return  gram.i.types.parametros.EmptyListaParametros.make() ;}private static  gram.i.types.Parametros  tom_cons_list_ListaParametros( gram.i.types.Parametros  e,  gram.i.types.Parametros  l) { return  gram.i.types.parametros.ConsListaParametros.make(e,l) ;}private static  gram.i.types.Parametros  tom_get_head_ListaParametros_Parametros( gram.i.types.Parametros  l) {return  l.getHeadListaParametros() ;}private static  gram.i.types.Parametros  tom_get_tail_ListaParametros_Parametros( gram.i.types.Parametros  l) {return  l.getTailListaParametros() ;}private static boolean tom_is_empty_ListaParametros_Parametros( gram.i.types.Parametros  l) {return  l.isEmptyListaParametros() ;}   private static   gram.i.types.Parametros  tom_append_list_ListaParametros( gram.i.types.Parametros  l1,  gram.i.types.Parametros  l2) {     if( l1.isEmptyListaParametros() ) {       return l2;     } else if( l2.isEmptyListaParametros() ) {       return l1;     } else if( ((l1 instanceof gram.i.types.parametros.ConsListaParametros) || (l1 instanceof gram.i.types.parametros.EmptyListaParametros)) ) {       if(  l1.getTailListaParametros() .isEmptyListaParametros() ) {         return  gram.i.types.parametros.ConsListaParametros.make( l1.getHeadListaParametros() ,l2) ;       } else {         return  gram.i.types.parametros.ConsListaParametros.make( l1.getHeadListaParametros() ,tom_append_list_ListaParametros( l1.getTailListaParametros() ,l2)) ;       }     } else {       return  gram.i.types.parametros.ConsListaParametros.make(l1,l2) ;     }   }   private static   gram.i.types.Parametros  tom_get_slice_ListaParametros( gram.i.types.Parametros  begin,  gram.i.types.Parametros  end, gram.i.types.Parametros  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyListaParametros()  ||  (end==tom_empty_list_ListaParametros()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  gram.i.types.parametros.ConsListaParametros.make((( ((begin instanceof gram.i.types.parametros.ConsListaParametros) || (begin instanceof gram.i.types.parametros.EmptyListaParametros)) )? begin.getHeadListaParametros() :begin),( gram.i.types.Parametros )tom_get_slice_ListaParametros((( ((begin instanceof gram.i.types.parametros.ConsListaParametros) || (begin instanceof gram.i.types.parametros.EmptyListaParametros)) )? begin.getTailListaParametros() :tom_empty_list_ListaParametros()),end,tail)) ;   }   private static boolean tom_is_fun_sym_ListaArgumentos( gram.i.types.Argumentos  t) {return  ((t instanceof gram.i.types.argumentos.ConsListaArgumentos) || (t instanceof gram.i.types.argumentos.EmptyListaArgumentos)) ;}private static  gram.i.types.Argumentos  tom_empty_list_ListaArgumentos() { return  gram.i.types.argumentos.EmptyListaArgumentos.make() ;}private static  gram.i.types.Argumentos  tom_cons_list_ListaArgumentos( gram.i.types.Argumentos  e,  gram.i.types.Argumentos  l) { return  gram.i.types.argumentos.ConsListaArgumentos.make(e,l) ;}private static  gram.i.types.Argumentos  tom_get_head_ListaArgumentos_Argumentos( gram.i.types.Argumentos  l) {return  l.getHeadListaArgumentos() ;}private static  gram.i.types.Argumentos  tom_get_tail_ListaArgumentos_Argumentos( gram.i.types.Argumentos  l) {return  l.getTailListaArgumentos() ;}private static boolean tom_is_empty_ListaArgumentos_Argumentos( gram.i.types.Argumentos  l) {return  l.isEmptyListaArgumentos() ;}   private static   gram.i.types.Argumentos  tom_append_list_ListaArgumentos( gram.i.types.Argumentos  l1,  gram.i.types.Argumentos  l2) {     if( l1.isEmptyListaArgumentos() ) {       return l2;     } else if( l2.isEmptyListaArgumentos() ) {       return l1;     } else if( ((l1 instanceof gram.i.types.argumentos.ConsListaArgumentos) || (l1 instanceof gram.i.types.argumentos.EmptyListaArgumentos)) ) {       if(  l1.getTailListaArgumentos() .isEmptyListaArgumentos() ) {         return  gram.i.types.argumentos.ConsListaArgumentos.make( l1.getHeadListaArgumentos() ,l2) ;       } else {         return  gram.i.types.argumentos.ConsListaArgumentos.make( l1.getHeadListaArgumentos() ,tom_append_list_ListaArgumentos( l1.getTailListaArgumentos() ,l2)) ;       }     } else {       return  gram.i.types.argumentos.ConsListaArgumentos.make(l1,l2) ;     }   }   private static   gram.i.types.Argumentos  tom_get_slice_ListaArgumentos( gram.i.types.Argumentos  begin,  gram.i.types.Argumentos  end, gram.i.types.Argumentos  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyListaArgumentos()  ||  (end==tom_empty_list_ListaArgumentos()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  gram.i.types.argumentos.ConsListaArgumentos.make((( ((begin instanceof gram.i.types.argumentos.ConsListaArgumentos) || (begin instanceof gram.i.types.argumentos.EmptyListaArgumentos)) )? begin.getHeadListaArgumentos() :begin),( gram.i.types.Argumentos )tom_get_slice_ListaArgumentos((( ((begin instanceof gram.i.types.argumentos.ConsListaArgumentos) || (begin instanceof gram.i.types.argumentos.EmptyListaArgumentos)) )? begin.getTailListaArgumentos() :tom_empty_list_ListaArgumentos()),end,tail)) ;   }   private static boolean tom_is_fun_sym_ListaDecl( gram.i.types.Declaracoes  t) {return  ((t instanceof gram.i.types.declaracoes.ConsListaDecl) || (t instanceof gram.i.types.declaracoes.EmptyListaDecl)) ;}private static  gram.i.types.Declaracoes  tom_empty_list_ListaDecl() { return  gram.i.types.declaracoes.EmptyListaDecl.make() ;}private static  gram.i.types.Declaracoes  tom_cons_list_ListaDecl( gram.i.types.Declaracoes  e,  gram.i.types.Declaracoes  l) { return  gram.i.types.declaracoes.ConsListaDecl.make(e,l) ;}private static  gram.i.types.Declaracoes  tom_get_head_ListaDecl_Declaracoes( gram.i.types.Declaracoes  l) {return  l.getHeadListaDecl() ;}private static  gram.i.types.Declaracoes  tom_get_tail_ListaDecl_Declaracoes( gram.i.types.Declaracoes  l) {return  l.getTailListaDecl() ;}private static boolean tom_is_empty_ListaDecl_Declaracoes( gram.i.types.Declaracoes  l) {return  l.isEmptyListaDecl() ;}   private static   gram.i.types.Declaracoes  tom_append_list_ListaDecl( gram.i.types.Declaracoes  l1,  gram.i.types.Declaracoes  l2) {     if( l1.isEmptyListaDecl() ) {       return l2;     } else if( l2.isEmptyListaDecl() ) {       return l1;     } else if( ((l1 instanceof gram.i.types.declaracoes.ConsListaDecl) || (l1 instanceof gram.i.types.declaracoes.EmptyListaDecl)) ) {       if(  l1.getTailListaDecl() .isEmptyListaDecl() ) {         return  gram.i.types.declaracoes.ConsListaDecl.make( l1.getHeadListaDecl() ,l2) ;       } else {         return  gram.i.types.declaracoes.ConsListaDecl.make( l1.getHeadListaDecl() ,tom_append_list_ListaDecl( l1.getTailListaDecl() ,l2)) ;       }     } else {       return  gram.i.types.declaracoes.ConsListaDecl.make(l1,l2) ;     }   }   private static   gram.i.types.Declaracoes  tom_get_slice_ListaDecl( gram.i.types.Declaracoes  begin,  gram.i.types.Declaracoes  end, gram.i.types.Declaracoes  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyListaDecl()  ||  (end==tom_empty_list_ListaDecl()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  gram.i.types.declaracoes.ConsListaDecl.make((( ((begin instanceof gram.i.types.declaracoes.ConsListaDecl) || (begin instanceof gram.i.types.declaracoes.EmptyListaDecl)) )? begin.getHeadListaDecl() :begin),( gram.i.types.Declaracoes )tom_get_slice_ListaDecl((( ((begin instanceof gram.i.types.declaracoes.ConsListaDecl) || (begin instanceof gram.i.types.declaracoes.EmptyListaDecl)) )? begin.getTailListaDecl() :tom_empty_list_ListaDecl()),end,tail)) ;   }   private static boolean tom_is_fun_sym_SeqInstrucao( gram.i.types.Instrucao  t) {return  ((t instanceof gram.i.types.instrucao.ConsSeqInstrucao) || (t instanceof gram.i.types.instrucao.EmptySeqInstrucao)) ;}private static  gram.i.types.Instrucao  tom_empty_list_SeqInstrucao() { return  gram.i.types.instrucao.EmptySeqInstrucao.make() ;}private static  gram.i.types.Instrucao  tom_cons_list_SeqInstrucao( gram.i.types.Instrucao  e,  gram.i.types.Instrucao  l) { return  gram.i.types.instrucao.ConsSeqInstrucao.make(e,l) ;}private static  gram.i.types.Instrucao  tom_get_head_SeqInstrucao_Instrucao( gram.i.types.Instrucao  l) {return  l.getHeadSeqInstrucao() ;}private static  gram.i.types.Instrucao  tom_get_tail_SeqInstrucao_Instrucao( gram.i.types.Instrucao  l) {return  l.getTailSeqInstrucao() ;}private static boolean tom_is_empty_SeqInstrucao_Instrucao( gram.i.types.Instrucao  l) {return  l.isEmptySeqInstrucao() ;}   private static   gram.i.types.Instrucao  tom_append_list_SeqInstrucao( gram.i.types.Instrucao  l1,  gram.i.types.Instrucao  l2) {     if( l1.isEmptySeqInstrucao() ) {       return l2;     } else if( l2.isEmptySeqInstrucao() ) {       return l1;     } else if( ((l1 instanceof gram.i.types.instrucao.ConsSeqInstrucao) || (l1 instanceof gram.i.types.instrucao.EmptySeqInstrucao)) ) {       if(  l1.getTailSeqInstrucao() .isEmptySeqInstrucao() ) {         return  gram.i.types.instrucao.ConsSeqInstrucao.make( l1.getHeadSeqInstrucao() ,l2) ;       } else {         return  gram.i.types.instrucao.ConsSeqInstrucao.make( l1.getHeadSeqInstrucao() ,tom_append_list_SeqInstrucao( l1.getTailSeqInstrucao() ,l2)) ;       }     } else {       return  gram.i.types.instrucao.ConsSeqInstrucao.make(l1,l2) ;     }   }   private static   gram.i.types.Instrucao  tom_get_slice_SeqInstrucao( gram.i.types.Instrucao  begin,  gram.i.types.Instrucao  end, gram.i.types.Instrucao  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptySeqInstrucao()  ||  (end==tom_empty_list_SeqInstrucao()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  gram.i.types.instrucao.ConsSeqInstrucao.make((( ((begin instanceof gram.i.types.instrucao.ConsSeqInstrucao) || (begin instanceof gram.i.types.instrucao.EmptySeqInstrucao)) )? begin.getHeadSeqInstrucao() :begin),( gram.i.types.Instrucao )tom_get_slice_SeqInstrucao((( ((begin instanceof gram.i.types.instrucao.ConsSeqInstrucao) || (begin instanceof gram.i.types.instrucao.EmptySeqInstrucao)) )? begin.getTailSeqInstrucao() :tom_empty_list_SeqInstrucao()),end,tail)) ;   }    private static boolean tom_equal_term_TreeSet(Object l1, Object l2) {return  l1.equals(l2) ;}private static boolean tom_is_sort_TreeSet(Object t) {return  t instanceof java.util.TreeSet ;}   private static java.util.TreeSet treeSetAppend(Object o, java.util.TreeSet s) {   java.util.TreeSet res = (java.util.TreeSet) s.clone();   res.add(o);   return res; }  private static Object treeSetGetHead(java.util.TreeSet s) {   java.util.Iterator it = s.iterator();   return it.next(); }  private static java.util.TreeSet treeSetGetTail(java.util.TreeSet s) {   java.util.TreeSet res = (java.util.TreeSet) s.clone();   java.util.Iterator it = s.iterator();   res.remove(it.next());   return res; }   







  private String actualFunctionName;
  HashMap<String, Argumentos> functionSignatures;
  HashMap<String, Argumentos> functionMap;
  HashMap<String, Integer> functionComments;
  private boolean callReturnNeeded;
  private int memAdress;
  StringBuilder functionsDeclarations;

  //Main Metrics Variables 
  String main_Description;
  double main_CYCLO;
  double main_LOC;
  double main_OPERATION;
  double main_NOM;
  double main_CLASS;
  double main_NOC;
  double main_PACKAGE;
  double main_CALLS;
  double main_FANOUT;
  double main_ANDC;
  double main_AHH;

  String metrics_STANDARDS;


  // Map of Metrics Variables 
  Integer numberFunctions;
  HashMap <String,Integer> functionsNumberParameters;
  HashMap <String,Integer> cyclomaticComplexityMap;
  HashMap <String,Integer> LongMap;
  HashMap <String,Integer> nOperationsMap;
  HashMap <String,Integer> nOperationsComparisonsMap;
  HashMap <String,Integer> opAtribMap;
  HashMap <String,Integer> nIncrDecrOpMap;
  HashMap <String,Integer> lnMap;
  HashMap <String,Integer> iplMap;
  HashMap <String,Integer> argsMap;
  Integer totalLinesOfCode;
  HashMap <String, TreeSet<String> > usedIdsMap;
  HashMap <String, Integer> numberIdsCallsMap;
  HashMap <String, TreeSet<String> > usedArgsMap;
  HashMap <String, TreeSet<String> > unusedArgsMap;
  HashMap <String, TreeSet<String> > unusedDeclarationsMap;
  HashMap <String, TreeSet <String> > operationsPerFunctionMap;
  HashMap <String , List<Double> > pyramidMetrics;
  HashMap <String , Integer> mapRefactor;

  /*** Separated Metrics ***/
  /** OpNum **/
  private static int nrMais;
  private static int nrVezes;
  private static int nrDivide;
  private static int nrMenos;
  private static int nrMod;

  /** OpComp **/
  private static int nrMaior;
  private static int nrMenor;
  private static int nrMaiorQ;
  private static int nrMenorQ;
  private static int nrDif;
  private static int nrIgual;

  /** OpInc **/
  private static int nrInc;
  private static int nrDec;

  /** OpAtribuicao **/
  private static int nrOpAtribAtrib;
  private static int nrOpAtribMult;
  private static int nrOpAtribDiv;
  private static int nrOpAtribSoma;
  private static int nrOpAtribSub;

  public Main() {
    this.main_Description = "global";
    this.main_CYCLO = 0.0;
    this.main_LOC = 0.0;
    this.main_OPERATION = 0.0;
    this.main_NOM = 0.0;
    this.main_CLASS = 0.0;
    this.main_NOC = 1.0;
    this.main_PACKAGE = 1.0;
    double main_CALLS = 0.0;
    double main_FANOUT = 0.0 ;
    double main_ANDC = 0.0;
    double main_AHH = 0.0;
    this.metrics_STANDARDS = "standards_c.txt";
    actualFunctionName = "";
    functionSignatures = new HashMap<String, Argumentos>();
    callReturnNeeded = true;
    functionsDeclarations = new StringBuilder();
    memAdress = 0;
    Integer numberFunctions = 0;
    functionsNumberParameters = new HashMap<String,Integer>();
    cyclomaticComplexityMap = new HashMap<String,Integer>();
    LongMap = new HashMap<String,Integer>();
    Integer totalLinesOfCode = 0;
    this.nOperationsMap = new HashMap <String,Integer>();
    this.operationsPerFunctionMap = new HashMap <String, TreeSet <String> >();
    this.nOperationsComparisonsMap = new HashMap <String,Integer>();
    this.nIncrDecrOpMap = new HashMap <String,Integer>();
    this.opAtribMap = new HashMap <String,Integer>();
    this.functionComments = new HashMap<String,Integer>();
    this.cyclomaticComplexityMap = new HashMap <String, Integer>();
    this.lnMap = new HashMap <String,Integer>();
    this.argsMap = new HashMap <String, Integer>();
    this.usedIdsMap = new HashMap <String, TreeSet<String> >();
    this.numberIdsCallsMap = new HashMap <String, Integer>();
    this.usedArgsMap = new HashMap <String, TreeSet<String> >();
    this.unusedArgsMap = new HashMap <String, TreeSet<String> >();
    this.unusedDeclarationsMap = new HashMap <String, TreeSet<String>>();
    this.iplMap = new HashMap <String,Integer>();
    this.pyramidMetrics = new HashMap <String, List<Double>>();
    this.mapRefactor = new HashMap <String, Integer> ();
  }

  public static void main(String[] args) {
    try {
      iLexer lexer = new iLexer(new ANTLRInputStream(System.in));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      iParser parser = new iParser(tokens);
      // Parse the input expression
      Tree b = (Tree) parser.prog().getTree();
      Instrucao p = (Instrucao) iAdaptor.getTerm(b);
      Main main = new Main();

      try {
        ArrayList<Integer> numInstrucao = new ArrayList<Integer>();
        numInstrucao.add(1);
        tom_make_TopDown(tom_make_CollectNumberFuncs(main.functionSignatures,main.argsMap)).visit(p);
        tom_make_TopDown(tom_make_CollectCiclomaticComplex(main.functionSignatures,main.cyclomaticComplexityMap)).visit(p);
        tom_make_TopDown(tom_make_CollectComments(main.functionSignatures,main.functionComments)).visit(p);
        tom_make_TopDown(tom_make_CollectOperations(main.functionSignatures,main.nOperationsMap,main.operationsPerFunctionMap)).visit(p);
        tom_make_TopDown(tom_make_CollectNumOperationsComparisons(main.functionSignatures,main.nOperationsComparisonsMap)).visit(p);
        tom_make_TopDown(tom_make_CollectNumOperationsIncrDecr(main.functionSignatures,main.nIncrDecrOpMap)).visit(p);
        tom_make_TopDown(tom_make_CollectOpAtrib(main.functionSignatures,main.opAtribMap)).visit(p);
        tom_make_TopDown(tom_make_collectIPL(main.iplMap)).visit(p);
        tom_make_TopDown(tom_make_collectUsedIdsMap(main.usedIdsMap,main.numberIdsCallsMap)).visit(p);
        tom_make_TopDown(tom_make_collectUnusedArguments(main.unusedArgsMap,main.usedIdsMap)).visit(p);
        tom_make_TopDown(tom_make_collectUnusedDeclarations(main.unusedDeclarationsMap,main.usedIdsMap)).visit(p);
        tom_make_TopDown(tom_make_collectLN(main.lnMap)).visit(p);

        Instrucao p2 = p;
        int numInst = numInstrucao.get(0)-1;
        LComentarios c = tom_make_Vazio();
        Expressao numInstExps = tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(numInst),c,c),tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Char("#"),c,c),tom_empty_list_Expressoes()));
        NumToInt n = new NumToInt(1);
        String numInstString = main.compileAnnotExpressoes(numInstExps, n);
        String instrucoes = "";
        if (args.length > 0) {
          if (args[0].equals("-fi") && args.length > 1) { 
            TreeSet<Integer> blocosMaisUsados = new TreeSet<Integer>();

            if(Main.parseFile(args[1],blocosMaisUsados)) { 
              numInstrucao.clear();
              numInstrucao.add(1);
              Instrucao p3 = tom_make_BottomUp(tom_make_stratFaultInjectionWithKnowledge(numInstrucao,blocosMaisUsados)).visit(p2);
              instrucoes = main.compileAnnot(p3);
            } else { 
              System.out.println("Failed to parse blocks"); 
            } 
          }
          else if (args[0].equals("-bs")) {
            for ( String funcao : main.functionSignatures.keySet()){
              TreeSet argsNaoUsados = main.unusedArgsMap.get(funcao);
              Integer refactor = argsNaoUsados.size();
              main.mapRefactor.put( funcao , refactor );
            }
            for ( String funcao : main.functionSignatures.keySet()){
              TreeSet declaNaoUsados = main.unusedDeclarationsMap.get(funcao);
              Integer refactorDec = declaNaoUsados.size();
              Integer refactorArgs = main.mapRefactor.get(funcao);
              if (refactorDec > refactorArgs ){
                main.mapRefactor.put( funcao, refactorDec );
              }
            }
            Instrucao p3 = tom_make_TopDown(tom_make_startBadSmells(main.usedIdsMap,main.mapRefactor,main.unusedDeclarationsMap)).visit(p);
            tom_make_TopDown(tom_make_CollectNumberFuncs(main.functionSignatures,main.argsMap)).visit(p3);
            tom_make_TopDown(tom_make_CollectCiclomaticComplex(main.functionSignatures,main.cyclomaticComplexityMap)).visit(p3);
            tom_make_TopDown(tom_make_CollectComments(main.functionSignatures,main.functionComments)).visit(p3);
            tom_make_TopDown(tom_make_CollectOperations(main.functionSignatures,main.nOperationsMap,main.operationsPerFunctionMap)).visit(p3);
            tom_make_TopDown(tom_make_CollectNumOperationsComparisons(main.functionSignatures,main.nOperationsComparisonsMap)).visit(p3);
            tom_make_TopDown(tom_make_CollectNumOperationsIncrDecr(main.functionSignatures,main.nIncrDecrOpMap)).visit(p3);
            tom_make_TopDown(tom_make_CollectOpAtrib(main.functionSignatures,main.opAtribMap)).visit(p3);
            tom_make_TopDown(tom_make_collectIPL(main.iplMap)).visit(p3);
            tom_make_TopDown(tom_make_collectUsedIdsMap(main.usedIdsMap,main.numberIdsCallsMap)).visit(p3);
            tom_make_TopDown(tom_make_collectUnusedArguments(main.unusedArgsMap,main.usedIdsMap)).visit(p3);
            tom_make_TopDown(tom_make_collectUnusedDeclarations(main.unusedDeclarationsMap,main.usedIdsMap)).visit(p3);
            tom_make_TopDown(tom_make_collectLN(main.lnMap)).visit(p3);
            instrucoes = main.compileAnnot(p3);
          }
          else {
            instrucoes = main.compileAnnot(p2);
          }
        }
        else {
          instrucoes = main.compileAnnot(p2);
        }
        String functionDeclarationsAndArguments = main.functionsDeclarations.toString();
        System.out.println(functionDeclarationsAndArguments + numInstString + instrucoes);
      } catch(VisitFailure e) {
        System.out.println("the strategy failed");
      }

      /* Export this representation to .dot file*/

      try{
        FileWriter out=new FileWriter("grafo.dot");
        Viewer.toDot(p,out);
      }
      catch (IOException e){
        System.out.println("ERROR in dot file"); 
      }

      /*************************************************** PRINTING METRICS VALUES ***********************************************************/

      /** 1) Metric to count number of functions **/
      main.numberFunctions = main.functionSignatures.size();
      try{
        File file = new File("metrics.txt");
        // creates the file
        file.createNewFile();
        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file); 
        // Writes the content to the file
        writer.write("Number of fuctions: " + main.numberFunctions +"\n");
        int maximo_complexidade = 0;  
        for ( String funcao : main.functionSignatures.keySet() ){
          int complexidadeActual =  main.cyclomaticComplexityMap.get(funcao); 
          if ( complexidadeActual > maximo_complexidade ){
            maximo_complexidade = complexidadeActual;
          }
        }
        main.main_CYCLO = maximo_complexidade;

        writer.write("Calculated Cyclomatic Complexity: "+ maximo_complexidade +"\n");
        /** 2) Metric to count the number of arguments per function **/
        writer.write("Number of Arguments per function:\n");
        for (String funcao : main.functionSignatures.keySet()){
          int nargs = main.argsMap.get(funcao);
          writer.write("\t" + funcao +  " : " + nargs + "\n");
        }

        /** 3) Metric to count Cyclomatic Complexity Per Function **/
        writer.write("Cyclomatic Complexity per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.cyclomaticComplexityMap.get(funcao)+"\n" );
        }

        /** 4) Metric to count Number Of Operators Per Function **/
        writer.write("Number Operators per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nOperationsMap.get(funcao)+"\n" );
        }

        /** 5) Metric to count Number Of Unique Operators Per Function **/
        writer.write("Number Of Unique Operators per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          TreeSet distinctOpps =  main.operationsPerFunctionMap.get(funcao);
          writer.write("\t" + funcao + " : "+ distinctOpps.size() + " \n" );
        }


        /** 6) Metric to count Number Of Operands Per Function **/
        writer.write("Number Operands per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.numberIdsCallsMap.get(funcao)+"\n" );
        }

        /** 7) Metric to count Number Of Unique Operands Per Function **/
        writer.write("Number Of Unique Operands per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          TreeSet distinctOperands =  main.usedIdsMap.get(funcao);
          writer.write("\t" + funcao + " : "+ distinctOperands.size() + " \n" );
        }

        /**
         * Halstead Measures (lifted from www.sei.cmu.edu and https://en.wikipedia.org/wiki/Halstead_complexity_measures)
         * =================
         * The Halstead measures are based on four scalar numbers derived directly
         * from a program's source code:
         * n1 = the number of distinct operators
         * n2 = the number of distinct operands
         * N1 = the total number of operators
         * N2 = the total number of operands
         * From these numbers, five measures are derived:
         * Measure Symbol Formula
         * Program length N N= N1 + N2
         * Program vocabulary n n= n1 + n2
         * Volume V V= N * (LOG2 n)
         * Difficulty D D= (n1/2) * (N2/n2)
         * Effort E E= D * V
         */

        /** 8) Metric to Compute Halstead Measures Per Function **/
        writer.write("Halstead Measures per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){

          int totalOperands = main.numberIdsCallsMap.get(funcao);
          int totalOperators =  main.nOperationsMap.get(funcao);
          int programLength = totalOperands + totalOperators;

          TreeSet distinctOperandsTree =  main.usedIdsMap.get(funcao);
          int distinctOperands =  distinctOperandsTree.size(); 

          TreeSet distinctOppsTree =  main.operationsPerFunctionMap.get(funcao);
          int distinctOperators =    distinctOppsTree.size() ;

          int  vocabulary = distinctOperators + distinctOperands;

          double volume, difficulty, effort, time_program, delivered_bugs;
          if (distinctOperators > 0 && distinctOperands > 0)
          {
            volume = programLength * Math.log(vocabulary)/Math.log(2);

            /*The difficulty measure is related to the difficulty of 
              the program to write or understand, e.g. when doing code review. */
            difficulty = distinctOperators/2 * (totalOperands/distinctOperands); 
            /**/
            effort = difficulty * volume;
            time_program = effort / 18;
            delivered_bugs = volume / 3000;
            writer.write("\t" + funcao + " : " + "\n" );
            writer.write("\t\tVolume: " + volume + "\n" );
            writer.write("\t\tDifficulty: " + difficulty + "\n" );
            writer.write("\t\tEffort: " + effort + "\n" );
            writer.write("\t\tTime required to program: " + time_program + "\n" );
            writer.write("\t\tNumber of delivered bugs: " + delivered_bugs + "\n" );
          }
        }

        /** 9) Metric to count the Number Of Comments Per Function **/
        int number_comments = 0;
        for ( String funcao : main.functionSignatures.keySet() ){
          int comments =  main.functionComments.get(funcao); 
          number_comments += comments;
        }
        writer.write("Total comments present on file: " +  number_comments + "\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.functionComments.get(funcao)+"\n" );
        }

        /** 9) Metric for counting total number of lines per function 
         * Blank lines should not be counted
         * Comments should not be counted
         **/ 
        writer.write("Total lines of Code (LOC):\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.lnMap.get(funcao)+"\n" );
        }

        /** 10) Metric to count number path length of instructions*/
        writer.write("Instructions Path Lenght:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.iplMap.get(funcao)+"\n" );
        }

        /** 11) Metric to count Number of Operations Comparisons **/
        writer.write("Total Number of Operations Comparisons:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nOperationsComparisonsMap.get(funcao)+"\n" );
        }    

        /** 12) Metric to count Number of Increment/Decrement Operations **/
        writer.write("Total Number of Increment/Decrement Operations:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nIncrDecrOpMap.get(funcao)+"\n" );
        }     

        /** 13) Metric to count Number of Atribuicao Operations **/
        writer.write("Total Number of Atrib/Mult/Div/Soma/Sub:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.opAtribMap.get(funcao)+"\n" );
        }

        /** 14) Unused arguments per function  **/
        writer.write("Unused arguments per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          TreeSet argsNaoUsados = main.unusedArgsMap.get(funcao);
          writer.write("\t" + funcao + " ( "+ argsNaoUsados.size() + " unused argument ): " );
          Iterator<Integer> iteratorArgs = argsNaoUsados.iterator();
          while (iteratorArgs.hasNext()) {
            writer.write(iteratorArgs.next() + " ");
          }
          writer.write("\n" );
        }
        /** 15) Unused declarations per function  **/
        writer.write("Unused declarations per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          TreeSet declaNaoUsados = main.unusedDeclarationsMap.get(funcao);
          writer.write("\t" + funcao + " ( "+ declaNaoUsados.size() + " unused declarations ): " );
          Iterator<Integer> iteratorDecla = declaNaoUsados.iterator();
          while (iteratorDecla.hasNext()) {
            writer.write(iteratorDecla.next() + " ");
          }
          writer.write("\n" );
        }
        /** 16) Refactoring per function  **/
        writer.write("Refactoring avaibility per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          Integer flag = main.mapRefactor.get(funcao);
          String flagS = "not possible";
          if (flag != null ){
            if ( flag > 0 ){
              flagS = "possible";
            }
            writer.write("\t" + funcao + " : "+  flagS +"\n" );
          }
        }

        /******* Printing Separated Metrics ********/
        writer.flush();
        writer.close();
        /**************************************************************************************************************/

        BufferedWriter output = null;
        try {
          File fileParser = new File("metrics_to_gui.txt");
          output = new BufferedWriter(new FileWriter(fileParser));
          for (String funcao : main.functionSignatures.keySet() ){
            double CYCLO = (double) main.cyclomaticComplexityMap.get(funcao) ;
            double LOC = (double) main.lnMap.get(funcao) ;
            main.main_LOC += LOC;
            double OPERATION = (double) main.nOperationsMap.get(funcao) ;
            main.main_OPERATION += OPERATION;
            TreeSet distinctOppsTree =  main.operationsPerFunctionMap.get(funcao);
            double NOM = (double) distinctOppsTree.size()  ;
            main.main_NOM+=NOM;
            double CLASS = 1.0;
            main.main_CLASS = 1.0;
            double NOC = 1.0;
            main.main_NOC = 1.0;
            double PACKAGE = 1.0;
            main.main_PACKAGE = 1.0;
            TreeSet distinctOpps =  main.operationsPerFunctionMap.get(funcao);
            double CALLS = (double) distinctOpps.size();
            main.main_CALLS += CALLS;
            double FANOUT = 1.0;
            main.main_FANOUT = 1.0;
            double ANDC = 0.0;
            double IPL = main.iplMap.get(funcao);
            double AHH = 1 / IPL;
            output.write(funcao+";");
            double CYCLO_PER_LOC = CYCLO / LOC;
            output.write ( String.format("%.2f", CYCLO_PER_LOC )  + ";" );
            double LOC_PER_OPERATION = LOC / OPERATION;
            output.write ( String.format("%.2f", LOC_PER_OPERATION )  + ";" );
            double NOM_PER_CLASS = NOM / CLASS;
            output.write ( String.format("%.2f", NOM_PER_CLASS )  + ";" );
            double NOC_PER_PACKAGE = NOC / PACKAGE;
            output.write ( String.format("%.2f", NOC_PER_PACKAGE )  + ";" );
            double CALLS_PER_OPERATION = CALLS / OPERATION;
            output.write ( String.format("%.2f", CALLS_PER_OPERATION )  + ";" );
            double FANOUT_PER_CALL = FANOUT / CALLS;
            output.write ( String.format("%.2f", FANOUT_PER_CALL )  + ";" );
            output.write ( String.format("%.2f", ANDC )  + ";" );
            output.write ( String.format("%.2f", AHH )  + ";" );
            output.write ( String.format("%.2f", CYCLO )  + ";" );
            output.write ( String.format("%.2f", LOC )  + ";" );
            output.write ( String.format("%.2f", NOM)  + ";" );
            output.write ( String.format("%.2f", NOC )  + ";" );
            output.write ( String.format("%.2f", PACKAGE )  + ";" );
            output.write ( String.format("%.2f", FANOUT )  + ";" );
            output.write ( String.format("%.2f", CALLS )  + ";" );

            int totalOperands = main.numberIdsCallsMap.get(funcao);
            int totalOperators =  main.nOperationsMap.get(funcao);
            int programLength = totalOperands + totalOperators;
            TreeSet distinctOperandsTree =  main.usedIdsMap.get(funcao);
            int distinctOperands =  distinctOperandsTree.size(); 
            int distinctOperators =    distinctOppsTree.size() ;
            int  vocabulary = distinctOperators + distinctOperands;

            double VOLUME, DIFFICULTY, EFFORT, DELIVERED_BUGS, HALSTEAD;
            VOLUME = programLength * Math.log(vocabulary)/Math.log(2);
            /*The difficulty measure is related to the difficulty of 
              the program to write or understand, e.g. when doing code review. */
            DIFFICULTY = distinctOperators/2 * (totalOperands/distinctOperands); 
            /**/
            EFFORT = DIFFICULTY * VOLUME;
            DELIVERED_BUGS = VOLUME / 3000;
            double Maintainability = Math.max(0.01 ,(171 - 5.2 * Math.log(VOLUME) - 0.23 * CYCLO - 16.2 * Math.log(LOC))*100 / 171);
            output.write ( String.format("%.2f", VOLUME )  + ";" );
            output.write ( String.format("%.2f", DIFFICULTY )  + ";" );
            output.write ( String.format("%.2f", EFFORT )  + ";" );
            output.write ( String.format("%.2f", DELIVERED_BUGS )  + ";" );
            output.write ( String.format("%.4f",  1.0 / Maintainability )  + ";" );
            double NOCOM = main.functionComments.get(funcao);

            TreeSet argsNaoUsados = main.unusedArgsMap.get(funcao);
            double UNARG = argsNaoUsados.size();
            TreeSet declaNaoUsados = main.unusedDeclarationsMap.get(funcao);
            double UNDEC = declaNaoUsados.size();
            double NEGNEG = 0.0;
            double NEGIF = 0.0;
            output.write ( String.format("%.2f", NOCOM )  + ";" );
            output.write ( String.format("%.2f", IPL )  + ";" );
            output.write ( String.format("%.2f", UNARG )  + ";" );
            output.write ( String.format("%.2f", UNDEC )  + ";" );
            output.write ( String.format("%.2f", NEGNEG )  + ";" );
            output.write ( String.format("%.2f", NEGIF )  + ";" );
            double NARGS = main.argsMap.get(funcao);
            output.write ( String.format("%.2f", NARGS )  + ";" );
           output.write (  main.metrics_STANDARDS + "\n");

          }
          /*  output.write("Full Evaluation"+";");
              double main_CYCLO_PER_LOC = main.main_CYCLO / main.main_LOC;
              output.write ( String.format("%.2f", main_CYCLO_PER_LOC )  + ";" );
              double main_LOC_PER_OPERATION = main.main_LOC / main.main_OPERATION;
              output.write ( String.format("%.2f", main_LOC_PER_OPERATION )  + ";" );
              double main_NOM_PER_CLASS = main.main_NOM / main.main_CLASS;
              output.write ( String.format("%.2f", main_NOM_PER_CLASS )  + ";" );
              double main_NOC_PER_PACKAGE = main.main_NOC / main.main_PACKAGE;
              output.write ( String.format("%.2f", main_NOC_PER_PACKAGE )  + ";" );
              double main_CALLS_PER_OPERATION = main.main_CALLS / main.main_OPERATION;
              output.write ( String.format("%.2f", main_CALLS_PER_OPERATION )  + ";" );
              double main_FANOUT_PER_CALL = main.main_FANOUT / main.main_CALLS;
              output.write ( String.format("%.2f", main_FANOUT_PER_CALL )  + ";" );
              output.write ( String.format("%.2f", main.main_ANDC )  + ";" );
              output.write ( String.format("%.2f", main.main_AHH )  + ";" );
              output.write ( String.format("%.2f", main.main_CYCLO )  + ";" );
              output.write ( String.format("%.2f", main.main_LOC )  + ";" );
              output.write ( String.format("%.2f", main.main_NOM)  + ";" );
              output.write ( String.format("%.2f", main.main_NOC )  + ";" );
              output.write ( String.format("%.2f", main.main_PACKAGE )  + ";" );
              output.write ( String.format("%.2f", main.main_FANOUT )  + ";" );
              output.write ( String.format("%.2f", main.main_CALLS )  + ";" );
              output.write (  main.metrics_STANDARDS + "\n");
           */
        } catch ( IOException e ) {
          e.printStackTrace();
        } finally {
          if ( output != null ) output.close();
        }
      }

      catch (IOException e){
        System.out.println("ERROR in metrics file"); 
      }

      /*Export code generated to .txt file*/
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /* Line number counting per function  */
  public static class collectLNInside extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  ln;private  String  funcao;public collectLNInside( java.util.HashMap  ln,  String  funcao) {super(tom_make_Identity());this.ln=ln;this.funcao=funcao;}public  java.util.HashMap  getln() {return ln;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {


        ln.put(tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg))), 2);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Atribuicao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ln.get(funcao);
        valor_mapa++;
        ln.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Declaracao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ln.get(funcao);
        valor_mapa++;
        ln.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ln.get(funcao);
        valor_mapa+=2;
        ln.put(funcao, valor_mapa);
        if (tom_get_slot_If_Instrucao2((( gram.i.types.Instrucao )((Object)tom__arg)))!= tom_make_Exp(tom_make_Empty()) ){
          valor_mapa = (int) ln.get(funcao);
          valor_mapa+=2;
          ln.put(funcao, valor_mapa);
        }
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_While((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ln.get(funcao);
        valor_mapa+=2;
        ln.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_For((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ln.get(funcao);
        valor_mapa+=2;
        ln.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Return((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ln.get(funcao);
        valor_mapa++;
        ln.put(funcao, valor_mapa);
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_collectLNInside( java.util.HashMap  t0,  String  t1) { return new collectLNInside(t0,t1);}



  /*Line number counting*/
  public static class collectLN extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  ln;public collectLN( java.util.HashMap  ln) {super(tom_make_Identity());this.ln=ln;}public  java.util.HashMap  getln() {return ln;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));


        ln.put(tom_nome, 2);
        tom_make_TopDown(tom_make_collectLNInside(ln,tom_nome)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_collectLN( java.util.HashMap  t0) { return new collectLN(t0);}public static class collectUsedIdsInside extends tom.library.sl.AbstractStrategyBasic {private  java.util.TreeSet  idsUtilizados;private  java.util.HashMap  numberIdsCallsMap;private  String  funcao;public collectUsedIdsInside( java.util.TreeSet  idsUtilizados,  java.util.HashMap  numberIdsCallsMap,  String  funcao) {super(tom_make_Identity());this.idsUtilizados=idsUtilizados;this.numberIdsCallsMap=numberIdsCallsMap;this.funcao=funcao;}public  java.util.TreeSet  getidsUtilizados() {return idsUtilizados;}public  java.util.HashMap  getnumberIdsCallsMap() {return numberIdsCallsMap;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Expressao(v)) {return ((T)visit_Expressao((( gram.i.types.Expressao )v),introspector));}if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Expressao  _visit_Expressao( gram.i.types.Expressao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Expressao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Expressao  visit_Expressao( gram.i.types.Expressao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_Id((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) {













 
        idsUtilizados.add(tom_get_slot_Id_Id((( gram.i.types.Expressao )((Object)tom__arg))));
        int valor_mapa = (int) numberIdsCallsMap.get(funcao);
        valor_mapa++;
        numberIdsCallsMap.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_IncAntes((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) {
 
        idsUtilizados.add(tom_get_slot_IncAntes_Id((( gram.i.types.Expressao )((Object)tom__arg))));
        int valor_mapa = (int) numberIdsCallsMap.get(funcao);
        valor_mapa++;
        numberIdsCallsMap.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_IncDepois((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) {
 
        idsUtilizados.add(tom_get_slot_IncDepois_Id((( gram.i.types.Expressao )((Object)tom__arg))));
        int valor_mapa = (int) numberIdsCallsMap.get(funcao);
        valor_mapa++;
        numberIdsCallsMap.put(funcao, valor_mapa);
      }}}}}return _visit_Expressao(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Atribuicao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {         idsUtilizados.add(tom_get_slot_Atribuicao_Id((( gram.i.types.Instrucao )((Object)tom__arg))));         int valor_mapa = (int) numberIdsCallsMap.get(funcao);         valor_mapa++;         numberIdsCallsMap.put(funcao, valor_mapa);       }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_collectUsedIdsInside( java.util.TreeSet  t0,  java.util.HashMap  t1,  String  t2) { return new collectUsedIdsInside(t0,t1,t2);}



  /*Line number counting*/
  public static class collectUsedIdsMap extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  usedIdsMap;private  java.util.HashMap  numberIdsCallsMap;public collectUsedIdsMap( java.util.HashMap  usedIdsMap,  java.util.HashMap  numberIdsCallsMap) {super(tom_make_Identity());this.usedIdsMap=usedIdsMap;this.numberIdsCallsMap=numberIdsCallsMap;}public  java.util.HashMap  getusedIdsMap() {return usedIdsMap;}public  java.util.HashMap  getnumberIdsCallsMap() {return numberIdsCallsMap;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));


        TreeSet <String> usedIds = new TreeSet <String> ();
        numberIdsCallsMap.put(tom_nome,0);
        tom_make_TopDown(tom_make_collectUsedIdsInside(usedIds,numberIdsCallsMap,tom_nome)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
        usedIdsMap.put(tom_nome, usedIds );
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_collectUsedIdsMap( java.util.HashMap  t0,  java.util.HashMap  t1) { return new collectUsedIdsMap(t0,t1);}



  /** Metric to compute total of Arguments per Function ***/ 
  public static class getUnusedArgsFunction extends tom.library.sl.AbstractStrategyBasic {private  java.util.TreeSet  unusedArgs;private  java.util.TreeSet  usedIds;public getUnusedArgsFunction( java.util.TreeSet  unusedArgs,  java.util.TreeSet  usedIds) {super(tom_make_Identity());this.unusedArgs=unusedArgs;this.usedIds=usedIds;}public  java.util.TreeSet  getunusedArgs() {return unusedArgs;}public  java.util.TreeSet  getusedIds() {return usedIds;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Argumentos(v)) {return ((T)visit_Argumentos((( gram.i.types.Argumentos )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Argumentos  _visit_Argumentos( gram.i.types.Argumentos  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Argumentos )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Argumentos  visit_Argumentos( gram.i.types.Argumentos  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Argumentos(((Object)tom__arg))) {if (tom_is_sort_Argumentos((( gram.i.types.Argumentos )((Object)tom__arg)))) {if (tom_is_fun_sym_Argumento((( gram.i.types.Argumentos )(( gram.i.types.Argumentos )((Object)tom__arg))))) { String  tom_id=tom_get_slot_Argumento_Id((( gram.i.types.Argumentos )((Object)tom__arg)));


        if ( ! (usedIds.contains( tom_id)) ){
          unusedArgs.add( tom_id);
        }
      }}}}}return _visit_Argumentos(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_getUnusedArgsFunction( java.util.TreeSet  t0,  java.util.TreeSet  t1) { return new getUnusedArgsFunction(t0,t1);}



  /* Metric to collect unused arguments ids from each function */
  public static class collectUnusedArguments extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  unusedArgumentsMap;private  java.util.HashMap  usedIdsMap;public collectUnusedArguments( java.util.HashMap  unusedArgumentsMap,  java.util.HashMap  usedIdsMap) {super(tom_make_Identity());this.unusedArgumentsMap=unusedArgumentsMap;this.usedIdsMap=usedIdsMap;}public  java.util.HashMap  getunusedArgumentsMap() {return unusedArgumentsMap;}public  java.util.HashMap  getusedIdsMap() {return usedIdsMap;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));


        TreeSet <String> unusedArgs = new TreeSet <String> ();
        TreeSet <String> usedIds = ( TreeSet <String> ) usedIdsMap.get(tom_nome);
        tom_make_TopDown(tom_make_getUnusedArgsFunction(unusedArgs,usedIds)).visit(tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))));
        unusedArgumentsMap.put(tom_nome, unusedArgs );
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_collectUnusedArguments( java.util.HashMap  t0,  java.util.HashMap  t1) { return new collectUnusedArguments(t0,t1);}



  /** Metric to compute total of Arguments per Function ***/ 
  public static class getUnusedDeclarationsFunction extends tom.library.sl.AbstractStrategyBasic {private  java.util.TreeSet  unusedDeclarations;private  java.util.TreeSet  usedIds;public getUnusedDeclarationsFunction( java.util.TreeSet  unusedDeclarations,  java.util.TreeSet  usedIds) {super(tom_make_Identity());this.unusedDeclarations=unusedDeclarations;this.usedIds=usedIds;}public  java.util.TreeSet  getunusedDeclarations() {return unusedDeclarations;}public  java.util.TreeSet  getusedIds() {return usedIds;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Declaracoes(v)) {return ((T)visit_Declaracoes((( gram.i.types.Declaracoes )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Declaracoes  _visit_Declaracoes( gram.i.types.Declaracoes  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Declaracoes )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Declaracoes  visit_Declaracoes( gram.i.types.Declaracoes  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Declaracoes(((Object)tom__arg))) {if (tom_is_sort_Declaracoes((( gram.i.types.Declaracoes )((Object)tom__arg)))) {if (tom_is_fun_sym_Decl((( gram.i.types.Declaracoes )(( gram.i.types.Declaracoes )((Object)tom__arg))))) { String  tom_id=tom_get_slot_Decl_Id((( gram.i.types.Declaracoes )((Object)tom__arg)));


        if (!( usedIds.contains( tom_id) )){
          unusedDeclarations.add( tom_id);
        }
      }}}}}return _visit_Declaracoes(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_getUnusedDeclarationsFunction( java.util.TreeSet  t0,  java.util.TreeSet  t1) { return new getUnusedDeclarationsFunction(t0,t1);}



  /* Metric to collect unused declarations from each function */
  public static class collectUnusedDeclarations extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  unusedDeclarationsMap;private  java.util.HashMap  usedIdsMap;public collectUnusedDeclarations( java.util.HashMap  unusedDeclarationsMap,  java.util.HashMap  usedIdsMap) {super(tom_make_Identity());this.unusedDeclarationsMap=unusedDeclarationsMap;this.usedIdsMap=usedIdsMap;}public  java.util.HashMap  getunusedDeclarationsMap() {return unusedDeclarationsMap;}public  java.util.HashMap  getusedIdsMap() {return usedIdsMap;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));


        TreeSet <String> unusedDeclarations = new TreeSet <String> ();
        TreeSet <String> usedIds = ( TreeSet <String> ) usedIdsMap.get(tom_nome);
        tom_make_TopDown(tom_make_getUnusedDeclarationsFunction(unusedDeclarations,usedIds)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
        unusedDeclarationsMap.put(tom_nome, unusedDeclarations );
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_collectUnusedDeclarations( java.util.HashMap  t0,  java.util.HashMap  t1) { return new collectUnusedDeclarations(t0,t1);}



  //Instruction Path lenght
  public static class collectIPLInside extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  ipl;private  String  funcao;public collectIPLInside( java.util.HashMap  ipl,  String  funcao) {super(tom_make_Identity());this.ipl=ipl;this.funcao=funcao;}public  java.util.HashMap  getipl() {return ipl;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {


        ipl.put(tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg))), 1);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Atribuicao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa++;
        ipl.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Declaracao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa++;
        ipl.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa+=1;
        ipl.put(funcao, valor_mapa);
        if (tom_get_slot_If_Instrucao2((( gram.i.types.Instrucao )((Object)tom__arg)))!= tom_make_Exp(tom_make_Empty()) ){
          valor_mapa = (int) ipl.get(funcao);
          valor_mapa+=1;
          ipl.put(funcao, valor_mapa);
        }
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_While((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa+=1;
        ipl.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_For((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa+=1;
        ipl.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Return((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa++;
        ipl.put(funcao, valor_mapa);
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_collectIPLInside( java.util.HashMap  t0,  String  t1) { return new collectIPLInside(t0,t1);}



  /*Line number counting*/
  public static class collectIPL extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  ipl;public collectIPL( java.util.HashMap  ipl) {super(tom_make_Identity());this.ipl=ipl;}public  java.util.HashMap  getipl() {return ipl;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));


        ipl.put(tom_nome, 1);
        tom_make_TopDown(tom_make_collectIPLInside(ipl,tom_nome)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_collectIPL( java.util.HashMap  t0) { return new collectIPL(t0);}public static class startCollectCyclomaticInside extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  complexidade;private  String  funcao;private  int  maximo_medido;public startCollectCyclomaticInside( java.util.HashMap  complexidade,  String  funcao,  int  maximo_medido) {super(tom_make_Identity());this.complexidade=complexidade;this.funcao=funcao;this.maximo_medido=maximo_medido;}public  java.util.HashMap  getcomplexidade() {return complexidade;}public  String  getfuncao() {return funcao;}public  int  getmaximo_medido() {return maximo_medido;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_For((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {






        maximo_medido++;
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa <= maximo_medido ){
          complexidade.put(funcao,maximo_medido);
        } 
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        maximo_medido++;
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa <= maximo_medido ){
          complexidade.put(funcao,maximo_medido);
        } 
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_While((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        maximo_medido++;
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa <= maximo_medido ){
          complexidade.put(funcao,maximo_medido);
        } 
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startCollectCyclomaticInside( java.util.HashMap  t0,  String  t1,  int  t2) { return new startCollectCyclomaticInside(t0,t1,t2);}public static class startCollectCyclomatic extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  complexidade;private  String  funcao;public startCollectCyclomatic( java.util.HashMap  complexidade,  String  funcao) {super(tom_make_Identity());this.complexidade=complexidade;this.funcao=funcao;}public  java.util.HashMap  getcomplexidade() {return complexidade;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_For((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {






        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa < 1 ){
          complexidade.put(funcao,1);
        } 
        tom_make_TopDown(tom_make_startCollectCyclomaticInside(complexidade,funcao,1)).visit(tom_get_slot_For_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa < 1 ){
          complexidade.put(funcao,1);
        } 
        tom_make_TopDown(tom_make_startCollectCyclomaticInside(complexidade,funcao,1)).visit(tom_get_slot_If_Instrucao1((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_While((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa < 1 ){
          complexidade.put(funcao,1);
        } 
        tom_make_TopDown(tom_make_startCollectCyclomaticInside(complexidade,funcao,1)).visit(tom_get_slot_While_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startCollectCyclomatic( java.util.HashMap  t0,  String  t1) { return new startCollectCyclomatic(t0,t1);}



  /** Metric to compute *** Number of Operations *** **/
  public static class startCollectNumOperations extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  numberOperations;private  String  funcao;public startCollectNumOperations( java.util.HashMap  numberOperations,  String  funcao) {super(tom_make_Identity());this.numberOperations=numberOperations;this.funcao=funcao;}public  java.util.HashMap  getnumberOperations() {return numberOperations;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_OpComp(v)) {return ((T)visit_OpComp((( gram.i.types.OpComp )v),introspector));}if (tom_is_sort_OpNum(v)) {return ((T)visit_OpNum((( gram.i.types.OpNum )v),introspector));}if (tom_is_sort_OpInc(v)) {return ((T)visit_OpInc((( gram.i.types.OpInc )v),introspector));}if (tom_is_sort_OpAtribuicao(v)) {return ((T)visit_OpAtribuicao((( gram.i.types.OpAtribuicao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpAtribuicao  _visit_OpAtribuicao( gram.i.types.OpAtribuicao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpAtribuicao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpInc  _visit_OpInc( gram.i.types.OpInc  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpInc )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpNum  _visit_OpNum( gram.i.types.OpNum  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpNum )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpComp  _visit_OpComp( gram.i.types.OpComp  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpComp )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpInc  visit_OpInc( gram.i.types.OpInc  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpInc(((Object)tom__arg))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom__arg)))) {if (tom_is_fun_sym_Inc((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom__arg))))) {








































































































        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }}}}{if (tom_is_sort_OpInc(((Object)tom__arg))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom__arg)))) {if (tom_is_fun_sym_Dec((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom__arg))))) {

        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }}}}}return _visit_OpInc(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.OpComp  visit_OpComp( gram.i.types.OpComp  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Maior((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Menor((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_MaiorQ((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_MenorQ((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Dif((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Igual((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}}return _visit_OpComp(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.OpNum  visit_OpNum( gram.i.types.OpNum  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Mais((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Vezes((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Divide((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Menos((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Mod((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}}return _visit_OpNum(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.OpAtribuicao  visit_OpAtribuicao( gram.i.types.OpAtribuicao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Atrib((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Mult((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Div((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Soma((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Sub((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         int valor_mapa = (int) numberOperations.get(funcao);         valor_mapa++;         numberOperations.put(funcao, valor_mapa);        }}}}}return _visit_OpAtribuicao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startCollectNumOperations( java.util.HashMap  t0,  String  t1) { return new startCollectNumOperations(t0,t1);}





  /** Metric to compute *** Number of Operations *** **/
  public static class startCollectDistinctOperations extends tom.library.sl.AbstractStrategyBasic {private  java.util.TreeSet  distinctOperations;public startCollectDistinctOperations( java.util.TreeSet  distinctOperations) {super(tom_make_Identity());this.distinctOperations=distinctOperations;}public  java.util.TreeSet  getdistinctOperations() {return distinctOperations;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_OpComp(v)) {return ((T)visit_OpComp((( gram.i.types.OpComp )v),introspector));}if (tom_is_sort_OpNum(v)) {return ((T)visit_OpNum((( gram.i.types.OpNum )v),introspector));}if (tom_is_sort_OpInc(v)) {return ((T)visit_OpInc((( gram.i.types.OpInc )v),introspector));}if (tom_is_sort_OpAtribuicao(v)) {return ((T)visit_OpAtribuicao((( gram.i.types.OpAtribuicao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpAtribuicao  _visit_OpAtribuicao( gram.i.types.OpAtribuicao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpAtribuicao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpInc  _visit_OpInc( gram.i.types.OpInc  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpInc )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpNum  _visit_OpNum( gram.i.types.OpNum  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpNum )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpComp  _visit_OpComp( gram.i.types.OpComp  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpComp )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpInc  visit_OpInc( gram.i.types.OpInc  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpInc(((Object)tom__arg))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom__arg)))) {if (tom_is_fun_sym_Inc((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom__arg))))) {
























































        distinctOperations.add("Inc()");
      }}}}{if (tom_is_sort_OpInc(((Object)tom__arg))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom__arg)))) {if (tom_is_fun_sym_Dec((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom__arg))))) {

        distinctOperations.add("Dec()");
      }}}}}return _visit_OpInc(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.OpComp  visit_OpComp( gram.i.types.OpComp  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Maior((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         distinctOperations.add("Maior()");       }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Menor((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         distinctOperations.add("Menor()");       }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_MaiorQ((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         distinctOperations.add("MaiorQ()");       }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_MenorQ((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         distinctOperations.add("MenorQ()");       }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Dif((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         distinctOperations.add("Dif()");       }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Igual((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {         distinctOperations.add("Igual()");       }}}}}return _visit_OpComp(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.OpNum  visit_OpNum( gram.i.types.OpNum  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Mais((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         distinctOperations.add("Mais()");       }}}}{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Vezes((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         distinctOperations.add("Vezes()");       }}}}{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Divide((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         distinctOperations.add("Divide()");       }}}}{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Menos((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         distinctOperations.add("Menos()");       }}}}{if (tom_is_sort_OpNum(((Object)tom__arg))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom__arg)))) {if (tom_is_fun_sym_Mod((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom__arg))))) {         distinctOperations.add("Mod()");       }}}}}return _visit_OpNum(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.OpAtribuicao  visit_OpAtribuicao( gram.i.types.OpAtribuicao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Atrib((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         distinctOperations.add("Atrib()");       }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Mult((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         distinctOperations.add("Mult()");       }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Div((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         distinctOperations.add("Div()");       }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Soma((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         distinctOperations.add("Soma()");       }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Sub((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {         distinctOperations.add("Sub()");       }}}}}return _visit_OpAtribuicao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startCollectDistinctOperations( java.util.TreeSet  t0) { return new startCollectDistinctOperations(t0);}public static class CollectOperations extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  func;private  java.util.HashMap  numberOperations;private  java.util.HashMap  distinctOperations;public CollectOperations( java.util.HashMap  func,  java.util.HashMap  numberOperations,  java.util.HashMap  distinctOperations) {super(tom_make_Identity());this.func=func;this.numberOperations=numberOperations;this.distinctOperations=distinctOperations;}public  java.util.HashMap  getfunc() {return func;}public  java.util.HashMap  getnumberOperations() {return numberOperations;}public  java.util.HashMap  getdistinctOperations() {return distinctOperations;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg))); gram.i.types.Instrucao  tom_inst=tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)));






        func.put(tom_nome, tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))));
        numberOperations.put(tom_nome,0);
        TreeSet distinctOpps = new TreeSet <String>();
        tom_make_TopDown(tom_make_startCollectNumOperations(numberOperations,tom_nome)).visit(tom_inst);
        tom_make_TopDown(tom_make_startCollectDistinctOperations(distinctOpps)).visit(tom_inst);
        distinctOperations.put(tom_nome,distinctOpps);
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CollectOperations( java.util.HashMap  t0,  java.util.HashMap  t1,  java.util.HashMap  t2) { return new CollectOperations(t0,t1,t2);}



  /** Metric to compute *** Number Of Comparisons Operations *** **/
  public static class startCollectNumOperationsComparisons extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  numComp;private  String  funcao;public startCollectNumOperationsComparisons( java.util.HashMap  numComp,  String  funcao) {super(tom_make_Identity());this.numComp=numComp;this.funcao=funcao;}public  java.util.HashMap  getnumComp() {return numComp;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_OpComp(v)) {return ((T)visit_OpComp((( gram.i.types.OpComp )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpComp  _visit_OpComp( gram.i.types.OpComp  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpComp )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpComp  visit_OpComp( gram.i.types.OpComp  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Maior((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {


        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Menor((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {

        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_MaiorQ((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {

        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_MenorQ((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {

        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Dif((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {

        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpComp(((Object)tom__arg))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom__arg)))) {if (tom_is_fun_sym_Igual((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom__arg))))) {

        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }}}}}return _visit_OpComp(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startCollectNumOperationsComparisons( java.util.HashMap  t0,  String  t1) { return new startCollectNumOperationsComparisons(t0,t1);}



  /** Metric to compute *** Number of increments/decrements *** (Increment and "Decrement" operations included)**/
  public static class startCollectNumOfIncrementsAndDecrements extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  incrNum;private  String  funcao;public startCollectNumOfIncrementsAndDecrements( java.util.HashMap  incrNum,  String  funcao) {super(tom_make_Identity());this.incrNum=incrNum;this.funcao=funcao;}public  java.util.HashMap  getincrNum() {return incrNum;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_OpInc(v)) {return ((T)visit_OpInc((( gram.i.types.OpInc )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpInc  _visit_OpInc( gram.i.types.OpInc  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpInc )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpInc  visit_OpInc( gram.i.types.OpInc  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpInc(((Object)tom__arg))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom__arg)))) {if (tom_is_fun_sym_Inc((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom__arg))))) {


        int valor_mapa = (int) incrNum.get(funcao);
        valor_mapa++;
        incrNum.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpInc(((Object)tom__arg))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom__arg)))) {if (tom_is_fun_sym_Dec((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom__arg))))) {

        int valor_mapa = (int) incrNum.get(funcao);
        valor_mapa++;
        incrNum.put(funcao, valor_mapa);
      }}}}}return _visit_OpInc(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startCollectNumOfIncrementsAndDecrements( java.util.HashMap  t0,  String  t1) { return new startCollectNumOfIncrementsAndDecrements(t0,t1);}



  /** Metric to compute *** Number of Atrib/Mult/Div/Soma/Sub operations **/
  public static class startCollectOpAtribOperations extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  numOpAtrib;private  String  funcao;public startCollectOpAtribOperations( java.util.HashMap  numOpAtrib,  String  funcao) {super(tom_make_Identity());this.numOpAtrib=numOpAtrib;this.funcao=funcao;}public  java.util.HashMap  getnumOpAtrib() {return numOpAtrib;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_OpAtribuicao(v)) {return ((T)visit_OpAtribuicao((( gram.i.types.OpAtribuicao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpAtribuicao  _visit_OpAtribuicao( gram.i.types.OpAtribuicao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.OpAtribuicao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.OpAtribuicao  visit_OpAtribuicao( gram.i.types.OpAtribuicao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Atrib((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {


        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Mult((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {

        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Div((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {

        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Soma((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {

        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom__arg))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom__arg)))) {if (tom_is_fun_sym_Sub((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom__arg))))) {

        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }}}}}return _visit_OpAtribuicao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startCollectOpAtribOperations( java.util.HashMap  t0,  String  t1) { return new startCollectOpAtribOperations(t0,t1);}



  /** Metric to compute total of Comments ***/ 
  public static class startCollectComments extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  comments;private  String  funcao;public startCollectComments( java.util.HashMap  comments,  String  funcao) {super(tom_make_Identity());this.comments=comments;this.funcao=funcao;}public  java.util.HashMap  getcomments() {return comments;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_LComentarios(v)) {return ((T)visit_LComentarios((( gram.i.types.LComentarios )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.LComentarios  _visit_LComentarios( gram.i.types.LComentarios  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.LComentarios )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.LComentarios  visit_LComentarios( gram.i.types.LComentarios  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_LComentarios(((Object)tom__arg))) {if (tom_is_sort_LComentarios((( gram.i.types.LComentarios )((Object)tom__arg)))) {if (tom_is_fun_sym_Comentario((( gram.i.types.LComentarios )(( gram.i.types.LComentarios )((Object)tom__arg))))) {


        int num_comentarios = (int) comments.get(funcao);
        num_comentarios++;
        comments.put(funcao, num_comentarios);
      }}}}}return _visit_LComentarios(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startCollectComments( java.util.HashMap  t0,  String  t1) { return new startCollectComments(t0,t1);}



  /** Metric to compute total of Arguments per Function ***/ 
  public static class countArgsFunction extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  mapArgs;private  String  funcao;public countArgsFunction( java.util.HashMap  mapArgs,  String  funcao) {super(tom_make_Identity());this.mapArgs=mapArgs;this.funcao=funcao;}public  java.util.HashMap  getmapArgs() {return mapArgs;}public  String  getfuncao() {return funcao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Argumentos(v)) {return ((T)visit_Argumentos((( gram.i.types.Argumentos )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Argumentos  _visit_Argumentos( gram.i.types.Argumentos  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Argumentos )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Argumentos  visit_Argumentos( gram.i.types.Argumentos  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Argumentos(((Object)tom__arg))) {if (tom_is_sort_Argumentos((( gram.i.types.Argumentos )((Object)tom__arg)))) {if (tom_is_fun_sym_Argumento((( gram.i.types.Argumentos )(( gram.i.types.Argumentos )((Object)tom__arg))))) {


        int num_args = (int) mapArgs.get(funcao);
        mapArgs.remove(funcao);
        num_args+=1;
        mapArgs.put(funcao, num_args);
      }}}}}return _visit_Argumentos(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_countArgsFunction( java.util.HashMap  t0,  String  t1) { return new countArgsFunction(t0,t1);}



  /** Metric to compute total number of functions ***/
  public static class CollectNumberFuncs extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  func;private  java.util.HashMap  mapArgs;public CollectNumberFuncs( java.util.HashMap  func,  java.util.HashMap  mapArgs) {super(tom_make_Identity());this.func=func;this.mapArgs=mapArgs;}public  java.util.HashMap  getfunc() {return func;}public  java.util.HashMap  getmapArgs() {return mapArgs;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg))); gram.i.types.Argumentos  tom_argumentos=tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg)));


        func.put(tom_nome, tom_argumentos);
        mapArgs.put(tom_nome, 0);
        tom_make_TopDown(tom_make_countArgsFunction(mapArgs,tom_nome)).visit(tom_argumentos);

      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CollectNumberFuncs( java.util.HashMap  t0,  java.util.HashMap  t1) { return new CollectNumberFuncs(t0,t1);}public static class CollectCiclomaticComplex extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  func;private  java.util.HashMap  complex_c;public CollectCiclomaticComplex( java.util.HashMap  func,  java.util.HashMap  complex_c) {super(tom_make_Identity());this.func=func;this.complex_c=complex_c;}public  java.util.HashMap  getfunc() {return func;}public  java.util.HashMap  getcomplex_c() {return complex_c;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));






        func.put(tom_nome, tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))));
        complex_c.put(tom_nome,0);
        tom_make_TopDown(tom_make_startCollectCyclomatic(complex_c,tom_nome)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CollectCiclomaticComplex( java.util.HashMap  t0,  java.util.HashMap  t1) { return new CollectCiclomaticComplex(t0,t1);}public static class CollectComments extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  func;private  java.util.HashMap  comments;public CollectComments( java.util.HashMap  func,  java.util.HashMap  comments) {super(tom_make_Identity());this.func=func;this.comments=comments;}public  java.util.HashMap  getfunc() {return func;}public  java.util.HashMap  getcomments() {return comments;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));






        func.put(tom_nome, tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))));
        comments.put(tom_nome, 0);
        tom_make_TopDown(tom_make_startCollectComments(comments,tom_nome)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CollectComments( java.util.HashMap  t0,  java.util.HashMap  t1) { return new CollectComments(t0,t1);}public static class CollectNumOperationsComparisons extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  func;private  java.util.HashMap  operationsNumComp;public CollectNumOperationsComparisons( java.util.HashMap  func,  java.util.HashMap  operationsNumComp) {super(tom_make_Identity());this.func=func;this.operationsNumComp=operationsNumComp;}public  java.util.HashMap  getfunc() {return func;}public  java.util.HashMap  getoperationsNumComp() {return operationsNumComp;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));






        func.put(tom_nome, tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))));
        operationsNumComp.put(tom_nome,0);
        tom_make_TopDown(tom_make_startCollectNumOperationsComparisons(operationsNumComp,tom_nome)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CollectNumOperationsComparisons( java.util.HashMap  t0,  java.util.HashMap  t1) { return new CollectNumOperationsComparisons(t0,t1);}public static class CollectNumOperationsIncrDecr extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  func;private  java.util.HashMap  incrDecrOp;public CollectNumOperationsIncrDecr( java.util.HashMap  func,  java.util.HashMap  incrDecrOp) {super(tom_make_Identity());this.func=func;this.incrDecrOp=incrDecrOp;}public  java.util.HashMap  getfunc() {return func;}public  java.util.HashMap  getincrDecrOp() {return incrDecrOp;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));






        func.put(tom_nome, tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))));
        incrDecrOp.put(tom_nome,0);
        tom_make_TopDown(tom_make_startCollectNumOfIncrementsAndDecrements(incrDecrOp,tom_nome)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CollectNumOperationsIncrDecr( java.util.HashMap  t0,  java.util.HashMap  t1) { return new CollectNumOperationsIncrDecr(t0,t1);}public static class CollectOpAtrib extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  func;private  java.util.HashMap  opAtribMap;public CollectOpAtrib( java.util.HashMap  func,  java.util.HashMap  opAtribMap) {super(tom_make_Identity());this.func=func;this.opAtribMap=opAtribMap;}public  java.util.HashMap  getfunc() {return func;}public  java.util.HashMap  getopAtribMap() {return opAtribMap;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));






        func.put(tom_nome, tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))));
        opAtribMap.put(tom_nome,0);
        tom_make_TopDown(tom_make_startCollectOpAtribOperations(opAtribMap,tom_nome)).visit(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CollectOpAtrib( java.util.HashMap  t0,  java.util.HashMap  t1) { return new CollectOpAtrib(t0,t1);}



  /***************************
   ** Refactoring Strategies **
   ***************************/

  public static class RefactorNegIf extends tom.library.sl.AbstractStrategyBasic {public RefactorNegIf() {super(tom_make_Identity());}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { gram.i.types.Expressao  tomMatch34_4=tom_get_slot_If_Condicao((( gram.i.types.Instrucao )((Object)tom__arg)));if (tom_is_sort_Expressao(tomMatch34_4)) {if (tom_is_fun_sym_Nao((( gram.i.types.Expressao )tomMatch34_4))) {

 return tom_make_If(tom_get_slot_If_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_Nao_Expressao(tomMatch34_4),tom_get_slot_If_c4((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Instrucao2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Instrucao1((( gram.i.types.Instrucao )((Object)tom__arg)))); }}}}}}}return _visit_Instrucao(tom__arg,introspector);}}public static class RefactorNegNeg extends tom.library.sl.AbstractStrategyBasic {public RefactorNegNeg() {super(tom_make_Identity());}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Expressao(v)) {return ((T)visit_Expressao((( gram.i.types.Expressao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Expressao  _visit_Expressao( gram.i.types.Expressao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Expressao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Expressao  visit_Expressao( gram.i.types.Expressao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_Nao((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) { gram.i.types.Expressao  tomMatch35_1=tom_get_slot_Nao_Expressao((( gram.i.types.Expressao )((Object)tom__arg)));if (tom_is_sort_Expressao(tomMatch35_1)) {if (tom_is_fun_sym_Nao((( gram.i.types.Expressao )tomMatch35_1))) {





 return tom_get_slot_Nao_Expressao(tomMatch35_1); }}}}}}}return _visit_Expressao(tom__arg,introspector);}}



  public static Argumentos removeArgumentosNaoUtilizados(Argumentos args, TreeSet<String> idsUtilizados) {
    {{if (tom_is_sort_Argumentos(((Object)args))) {if (tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )(( gram.i.types.Argumentos )((Object)args))))) {if (!( ( tom_is_empty_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)args))) || tom_equal_term_Argumentos((( gram.i.types.Argumentos )((Object)args)), tom_empty_list_ListaArgumentos()) ) )) { gram.i.types.Argumentos  tom_arg1=((tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )((Object)args))))?(tom_get_head_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)args)))):((( gram.i.types.Argumentos )((Object)args)))); gram.i.types.Argumentos  tom_tailArg=((tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )((Object)args))))?(tom_get_tail_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)args)))):(tom_empty_list_ListaArgumentos()));{{if (tom_is_sort_Argumentos(((Object)tom_arg1))) {if (tom_is_sort_Argumentos((( gram.i.types.Argumentos )((Object)tom_arg1)))) {if (tom_is_fun_sym_Argumento((( gram.i.types.Argumentos )(( gram.i.types.Argumentos )((Object)tom_arg1))))) {



            if (idsUtilizados.contains(tom_get_slot_Argumento_Id((( gram.i.types.Argumentos )((Object)tom_arg1)))))
              return tom_cons_list_ListaArgumentos((( gram.i.types.Argumentos )((Object)tom_arg1)),tom_cons_list_ListaArgumentos(removeArgumentosNaoUtilizados(tom_tailArg,idsUtilizados),tom_empty_list_ListaArgumentos()));
            else
              return removeArgumentosNaoUtilizados(tom_tailArg,idsUtilizados);
          }}}}}

      }}}}}

    return args;
  }

  public static Instrucao removeDeclaracoesNaoUtilizadas ( Instrucao inst, TreeSet<String> idsNaoUtilizados ) {
    {{if (tom_is_sort_Instrucao(((Object)inst))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)inst)))) {if (tom_is_fun_sym_Declaracao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)inst))))) { gram.i.types.Declaracoes  tom_declaracoes=tom_get_slot_Declaracao_Declaracoes((( gram.i.types.Instrucao )((Object)inst)));{{if (tom_is_sort_Declaracoes(((Object)tom_declaracoes))) {if (tom_is_fun_sym_ListaDecl((( gram.i.types.Declaracoes )(( gram.i.types.Declaracoes )((Object)tom_declaracoes))))) {if (!( ( tom_is_empty_ListaDecl_Declaracoes((( gram.i.types.Declaracoes )((Object)tom_declaracoes))) || tom_equal_term_Declaracoes((( gram.i.types.Declaracoes )((Object)tom_declaracoes)), tom_empty_list_ListaDecl()) ) )) { gram.i.types.Declaracoes  tom_dec1=((tom_is_fun_sym_ListaDecl((( gram.i.types.Declaracoes )((Object)tom_declaracoes))))?(tom_get_head_ListaDecl_Declaracoes((( gram.i.types.Declaracoes )((Object)tom_declaracoes)))):((( gram.i.types.Declaracoes )((Object)tom_declaracoes))));{{if (tom_is_sort_Declaracoes(((Object)tom_dec1))) {if (tom_is_sort_Declaracoes((( gram.i.types.Declaracoes )((Object)tom_dec1)))) {if (tom_is_fun_sym_Decl((( gram.i.types.Declaracoes )(( gram.i.types.Declaracoes )((Object)tom_dec1))))) {





                if ( idsNaoUtilizados.contains(tom_get_slot_Decl_Id((( gram.i.types.Declaracoes )((Object)tom_dec1)))))
                  return tom_make_Exp(tom_make_Empty());
              }}}}}

          }}}}}

      }}}}}

    return inst;
  }

  public static class startBadSmells extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  mapaIdsUtilizados;private  java.util.HashMap  mapaRefactor;private  java.util.HashMap  mapaIdsNaoUtilizados;public startBadSmells( java.util.HashMap  mapaIdsUtilizados,  java.util.HashMap  mapaRefactor,  java.util.HashMap  mapaIdsNaoUtilizados) {super(tom_make_Identity());this.mapaIdsUtilizados=mapaIdsUtilizados;this.mapaRefactor=mapaRefactor;this.mapaIdsNaoUtilizados=mapaIdsNaoUtilizados;}public  java.util.HashMap  getmapaIdsUtilizados() {return mapaIdsUtilizados;}public  java.util.HashMap  getmapaRefactor() {return mapaRefactor;}public  java.util.HashMap  getmapaIdsNaoUtilizados() {return mapaIdsNaoUtilizados;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { gram.i.types.Expressao  tomMatch41_4=tom_get_slot_If_Condicao((( gram.i.types.Instrucao )((Object)tom__arg)));if (tom_is_sort_Expressao(tomMatch41_4)) {if (tom_is_fun_sym_Nao((( gram.i.types.Expressao )tomMatch41_4))) {


        return tom_make_If(tom_get_slot_If_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_Nao_Expressao(tomMatch41_4),tom_get_slot_If_c4((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Instrucao2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Instrucao1((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg)));

        Integer refactorFlag = (Integer) mapaRefactor.get(tom_nome);
        if ( refactorFlag > 0 ){
          TreeSet<String> idsUtilizados = (TreeSet<String>) mapaIdsUtilizados.get(tom_nome);
          TreeSet<String> idsNaoUtilizados = (TreeSet<String>) mapaIdsNaoUtilizados.get(tom_nome);
          Argumentos args = removeArgumentosNaoUtilizados(tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))),idsUtilizados);
          Instrucao instrucoes = removeDeclaracoesNaoUtilizadas (tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))), idsNaoUtilizados);
          tom_nome= tom_nome+ "_refactored";
          return tom_make_Funcao(tom_get_slot_Funcao_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_Funcao_DefTipo((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_Funcao_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_nome,tom_get_slot_Funcao_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_Funcao_c4((( gram.i.types.Instrucao )((Object)tom__arg))),args,tom_get_slot_Funcao_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_Funcao_c6((( gram.i.types.Instrucao )((Object)tom__arg))),instrucoes,tom_get_slot_Funcao_c7((( gram.i.types.Instrucao )((Object)tom__arg))));
        }
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_startBadSmells( java.util.HashMap  t0,  java.util.HashMap  t1,  java.util.HashMap  t2) { return new startBadSmells(t0,t1,t2);}public static class CollectFuncsSignature extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashMap  signatures;public CollectFuncsSignature( java.util.HashMap  signatures) {super(tom_make_Identity());this.signatures=signatures;}public  java.util.HashMap  getsignatures() {return signatures;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {






        signatures.put(tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)tom__arg))), tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}public static class stratPrintAnnotations extends tom.library.sl.AbstractStrategyBasic {private  java.util.ArrayList  numInstrucao;public stratPrintAnnotations( java.util.ArrayList  numInstrucao) {super(tom_make_Identity());this.numInstrucao=numInstrucao;}public  java.util.ArrayList  getnumInstrucao() {return numInstrucao;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Expressao(v)) {return ((T)visit_Expressao((( gram.i.types.Expressao )v),introspector));}if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Expressao  _visit_Expressao( gram.i.types.Expressao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Expressao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Expressao  visit_Expressao( gram.i.types.Expressao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_ExpNum((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) { gram.i.types.Expressao  tom_e=(( gram.i.types.Expressao )((Object)tom__arg));





















































        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = tom_make_Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return tom_cons_list_Expressoes(tom_e,tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Char(","),c,c),tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(num),c,c),tom_empty_list_Expressoes())));
        else
          return tom_cons_list_Expressoes(tom_e,tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(num),c,c),tom_empty_list_Expressoes()));
      }}}}{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_Ou((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) { gram.i.types.Expressao  tom_e=(( gram.i.types.Expressao )((Object)tom__arg));

        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = tom_make_Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return tom_cons_list_Expressoes(tom_e,tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Char(","),c,c),tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(num),c,c),tom_empty_list_Expressoes())));
        else
          return tom_cons_list_Expressoes(tom_e,tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(num),c,c),tom_empty_list_Expressoes()));
      }}}}{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_E((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) { gram.i.types.Expressao  tom_e=(( gram.i.types.Expressao )((Object)tom__arg));

        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = tom_make_Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return tom_cons_list_Expressoes(tom_e,tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Char(","),c,c),tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(num),c,c),tom_empty_list_Expressoes())));
        else
          return tom_cons_list_Expressoes(tom_e,tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(num),c,c),tom_empty_list_Expressoes()));
      }}}}{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_Comp((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) { gram.i.types.Expressao  tom_e=(( gram.i.types.Expressao )((Object)tom__arg));

        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = tom_make_Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return tom_cons_list_Expressoes(tom_e,tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Char(","),c,c),tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(num),c,c),tom_empty_list_Expressoes())));
        else
          return tom_cons_list_Expressoes(tom_e,tom_cons_list_Expressoes(tom_make_Print(c,c,c,tom_make_Int(num),c,c),tom_empty_list_Expressoes()));
      }}}}}return _visit_Expressao(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Atribuicao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { gram.i.types.Instrucao  tom_i=(( gram.i.types.Instrucao )((Object)tom__arg));         int num = (Integer) numInstrucao.remove((int) 0);         LComentarios c = tom_make_Vazio();         numInstrucao.add(num+1);         if (num > 1)           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Char(","),c,c)),tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao())));         else           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao()));       }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { gram.i.types.Instrucao  tom_i=(( gram.i.types.Instrucao )((Object)tom__arg));         int num = (Integer) numInstrucao.remove((int) 0);         LComentarios c = tom_make_Vazio();         numInstrucao.add(num+1);         if (num > 1)           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Char(","),c,c)),tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao())));         else           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao()));       }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_While((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { gram.i.types.Instrucao  tom_i=(( gram.i.types.Instrucao )((Object)tom__arg));         int num = (Integer) numInstrucao.remove((int) 0);         LComentarios c = tom_make_Vazio();         numInstrucao.add(num+1);         if (num > 1)           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Char(","),c,c)),tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao())));         else           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao()));       }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_For((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { gram.i.types.Instrucao  tom_i=(( gram.i.types.Instrucao )((Object)tom__arg));         int num = (Integer) numInstrucao.remove((int) 0);         LComentarios c = tom_make_Vazio();         numInstrucao.add(num+1);         if (num > 1)           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Char(","),c,c)),tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao())));         else           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao()));       }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Return((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) { gram.i.types.Instrucao  tom_i=(( gram.i.types.Instrucao )((Object)tom__arg));         int num = (Integer) numInstrucao.remove((int) 0);         LComentarios c = tom_make_Vazio();         numInstrucao.add(num+1);         if (num > 1)           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Char(","),c,c)),tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao())));         else           return tom_cons_list_SeqInstrucao(tom_i,tom_cons_list_SeqInstrucao(tom_make_Exp(tom_make_Print(c,c,c,tom_make_Int(num),c,c)),tom_empty_list_SeqInstrucao()));       }}}}}return _visit_Instrucao(tom__arg,introspector);}}public static class stratFaultInjection extends tom.library.sl.AbstractStrategyBasic {public stratFaultInjection() {super(tom_make_Identity());}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {






        return tom_make_If(tom_get_slot_If_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Condicao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c4((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Instrucao2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Instrucao1((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_While((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        return tom_make_While(tom_get_slot_While_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_make_Nao(tom_get_slot_While_Condicao((( gram.i.types.Instrucao )((Object)tom__arg)))),tom_get_slot_While_c4((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_c6((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_For((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {

        return tom_make_For(tom_get_slot_For_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_Declaracao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_make_Nao(tom_get_slot_For_Condicao((( gram.i.types.Instrucao )((Object)tom__arg)))),tom_get_slot_For_c4((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_Expressao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c6((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c7((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c8((( gram.i.types.Instrucao )((Object)tom__arg))));
      }}}}}return _visit_Instrucao(tom__arg,introspector);}}public static class stratFaultInjectionWithKnowledge extends tom.library.sl.AbstractStrategyBasic {private  java.util.ArrayList  numInstrucao;private  java.util.Set  blocos;public stratFaultInjectionWithKnowledge( java.util.ArrayList  numInstrucao,  java.util.Set  blocos) {super(tom_make_Identity());this.numInstrucao=numInstrucao;this.blocos=blocos;}public  java.util.ArrayList  getnumInstrucao() {return numInstrucao;}public  java.util.Set  getblocos() {return blocos;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (tom_is_sort_Expressao(v)) {return ((T)visit_Expressao((( gram.i.types.Expressao )v),introspector));}if (tom_is_sort_Instrucao(v)) {return ((T)visit_Instrucao((( gram.i.types.Instrucao )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  _visit_Instrucao( gram.i.types.Instrucao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Instrucao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Expressao  _visit_Expressao( gram.i.types.Expressao  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( gram.i.types.Expressao )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  gram.i.types.Expressao  visit_Expressao( gram.i.types.Expressao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_ExpNum((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) {




































        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }}}}{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_Ou((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) {

        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }}}}{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_E((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) {

        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }}}}{if (tom_is_sort_Expressao(((Object)tom__arg))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)tom__arg)))) {if (tom_is_fun_sym_Comp((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)tom__arg))))) {

        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }}}}}return _visit_Expressao(tom__arg,introspector);}@SuppressWarnings("unchecked")public  gram.i.types.Instrucao  visit_Instrucao( gram.i.types.Instrucao  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Atribuicao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {         int num = (Integer) numInstrucao.remove((int) 0);         numInstrucao.add(num+1);         if (blocos.contains((Integer) num))           return (( gram.i.types.Instrucao )((Object)tom__arg));       }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {         int num = (Integer) numInstrucao.remove((int) 0);         numInstrucao.add(num+1);         if (blocos.contains((Integer) num))           return tom_make_If(tom_get_slot_If_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Condicao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c4((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Instrucao2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_If_Instrucao1((( gram.i.types.Instrucao )((Object)tom__arg))));       }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_While((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {         int num = (Integer) numInstrucao.remove((int) 0);         numInstrucao.add(num+1);         if (blocos.contains((Integer) num))           return tom_make_While(tom_get_slot_While_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_make_Nao(tom_get_slot_While_Condicao((( gram.i.types.Instrucao )((Object)tom__arg)))),tom_get_slot_While_c4((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_While_c6((( gram.i.types.Instrucao )((Object)tom__arg))));       }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_For((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {         int num = (Integer) numInstrucao.remove((int) 0);         numInstrucao.add(num+1);         if (blocos.contains((Integer) num))           return tom_make_For(tom_get_slot_For_c1((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c2((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_Declaracao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c3((( gram.i.types.Instrucao )((Object)tom__arg))),tom_make_Nao(tom_get_slot_For_Condicao((( gram.i.types.Instrucao )((Object)tom__arg)))),tom_get_slot_For_c4((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c5((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_Expressao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c6((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c7((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg))),tom_get_slot_For_c8((( gram.i.types.Instrucao )((Object)tom__arg))));       }}}}{if (tom_is_sort_Instrucao(((Object)tom__arg))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)tom__arg)))) {if (tom_is_fun_sym_Return((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)tom__arg))))) {         int num = (Integer) numInstrucao.remove((int) 0);         numInstrucao.add(num+1);       }}}}}return _visit_Instrucao(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_stratFaultInjectionWithKnowledge( java.util.ArrayList  t0,  java.util.Set  t1) { return new stratFaultInjectionWithKnowledge(t0,t1);}



  private String compileAnnot(Instrucao inst) {
    NumToInt numInstrucao = new NumToInt(1);
    String toReturn = compileAnnotInstrucao(inst, numInstrucao);
    //return toReturn.concat("Halt");
    return toReturn.substring(0,toReturn.length()-1);
  }

  private String compileAnnotInstrucao(Instrucao i, NumToInt numInstrucao) {
    {{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)i)))) {if (tom_is_fun_sym_Atribuicao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) { String  tom_id=tom_get_slot_Atribuicao_Id((( gram.i.types.Instrucao )((Object)i))); gram.i.types.OpAtribuicao  tom_opAtrib=tom_get_slot_Atribuicao_op((( gram.i.types.Instrucao )((Object)i)));

        String genExp = compileAnnotExpressoes(tom_get_slot_Atribuicao_Expressao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        {{if (tom_is_sort_OpAtribuicao(((Object)tom_opAtrib))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom_opAtrib)))) {if (tom_is_fun_sym_Atrib((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom_opAtrib))))) {
 return "Pusha \"" + prefix + tom_id+ "\"," + genExp + "Store,"; }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom_opAtrib))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom_opAtrib)))) {if (tom_is_fun_sym_Mult((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom_opAtrib))))) {
 return "Pusha \"" + prefix + tom_id+ "\",Pusha \"" + prefix + tom_id+ "\",Load," + genExp + "Mul,Store,"; }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom_opAtrib))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom_opAtrib)))) {if (tom_is_fun_sym_Div((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom_opAtrib))))) {
 return "Pusha \"" + prefix + tom_id+ "\",Pusha \"" + prefix + tom_id+ "\",Load," + genExp + "Div,Store,"; }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom_opAtrib))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom_opAtrib)))) {if (tom_is_fun_sym_Soma((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom_opAtrib))))) {
 return "Pusha \"" + prefix + tom_id+ "\",Pusha \"" + prefix + tom_id+ "\",Load," + genExp + "Add,Store,"; }}}}{if (tom_is_sort_OpAtribuicao(((Object)tom_opAtrib))) {if (tom_is_sort_OpAtribuicao((( gram.i.types.OpAtribuicao )((Object)tom_opAtrib)))) {if (tom_is_fun_sym_Sub((( gram.i.types.OpAtribuicao )(( gram.i.types.OpAtribuicao )((Object)tom_opAtrib))))) {
 return "Pusha \"" + prefix + tom_id+ "\",Pusha \"" + prefix + tom_id+ "\",Load," + genExp + "Sub,Store,"; }}}}}

        return "";
      }}}}{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)i)))) {if (tom_is_fun_sym_Declaracao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) {


        String genDecl = compileAnnotDeclaracoes(tom_get_slot_Declaracao_Declaracoes((( gram.i.types.Instrucao )((Object)i))),tom_get_slot_Declaracao_DefTipo((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        functionsDeclarations.append(genDecl);
        return "";
      }}}}{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)i)))) {if (tom_is_fun_sym_If((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) {


        String genCondicao = compileAnnotExpressoes(tom_get_slot_If_Condicao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String genInst1 = compileAnnotInstrucao(tom_get_slot_If_Instrucao1((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String genInst2 = compileAnnotInstrucao(tom_get_slot_If_Instrucao2((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        int num = numInstrucao.inc();

        return genCondicao + "Jumpf \"senao" + num + "\"," + genInst1 + "Jump \"fse" + num + "\",ALabel \"senao" + num + "\"," + genInst2 + "ALabel \"fse" + num + "\",";
      }}}}{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)i)))) {if (tom_is_fun_sym_While((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) {


        String genCondicao = compileAnnotExpressoes(tom_get_slot_While_Condicao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String genInst = compileAnnotInstrucao(tom_get_slot_While_Instrucao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        int num = numInstrucao.inc();

        return "ALabel \"enq" + num + "\"," + genCondicao + "Jumpf \"fenq" + num + "\"," + genInst + "Jump \"enq" + num +"\"," + "ALabel \"fenq" + num + "\",";
      }}}}{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)i)))) {if (tom_is_fun_sym_For((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) {


        String genDecl = compileAnnotInstrucao(tom_get_slot_For_Declaracao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String genCondicao = compileAnnotExpressoes(tom_get_slot_For_Condicao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String genExp = compileAnnotExpressoes(tom_get_slot_For_Expressao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String genInst = compileAnnotInstrucao(tom_get_slot_For_Instrucao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);

        int num = numInstrucao.inc();
        String labelInit = "ALabel \"for" + num + "\",";
        String jump = "Jumpf \"ffor"+ num + "\",";
        String labelJump = "ALabel \"ffor" + num + "\",";
        String labelEnd = "Jump \"for" + num + "\",";

        functionsDeclarations.append(genDecl);

        return labelInit.concat(genCondicao).concat(jump).concat(genInst).concat(genExp).concat(labelEnd).concat(labelJump);
      }}}}{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)i)))) {if (tom_is_fun_sym_Return((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) {


        String genExp = compileAnnotExpressoes(tom_get_slot_Return_Expressao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String prefix = "f:";
        String ret = "Ret,";
        String storeVarFunct = "Pusha \"" + prefix + actualFunctionName + "\"," + genExp + "Store,";

        return storeVarFunct;
      }}}}{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)i)))) {if (tom_is_fun_sym_Funcao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) { gram.i.types.DefTipo  tom_tipo=tom_get_slot_Funcao_DefTipo((( gram.i.types.Instrucao )((Object)i))); String  tom_nome=tom_get_slot_Funcao_Nome((( gram.i.types.Instrucao )((Object)i)));


        int actualMemAddress = memAdress;
        memAdress++;
        int sizeAddress = 1;

        actualFunctionName = tom_nome;
        String prefix = "f:";
        String functionDeclaration = "Decl \"" + prefix + tom_nome+ "\" " + actualMemAddress + " " +  sizeAddress + ",";
        String functionRet = "";
        {{if (tom_is_sort_DefTipo(((Object)tom_tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tom_tipo)))) {if (tom_is_fun_sym_DVoid((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tom_tipo))))) {
 if (!actualFunctionName.equals("main")) functionRet = "Ret,"; }}}}{if (tom_is_sort_DefTipo(((Object)tom_tipo))) {
 if(!actualFunctionName.equals("main")) functionRet = "Ret,"; }}}

        String halt = actualFunctionName.equals("main") ? "Halt," : "";
        String genArgs = compileArguments(tom_nome,tom_get_slot_Funcao_Argumentos((( gram.i.types.Instrucao )((Object)i))));

        functionsDeclarations.append(functionDeclaration);
        functionsDeclarations.append(genArgs);

        String genInst = compileAnnotInstrucao(tom_get_slot_Funcao_Instrucao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        String function = "ALabel \"f:" + tom_nome+ "\"," + genInst + functionRet + halt;

        return function;
      }}}}{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_sort_Instrucao((( gram.i.types.Instrucao )((Object)i)))) {if (tom_is_fun_sym_Exp((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) {


        callReturnNeeded = false;
        String exp = compileAnnotExpressoes(tom_get_slot_Exp_Expressao((( gram.i.types.Instrucao )((Object)i))),numInstrucao);
        callReturnNeeded = true;

        return exp;
      }}}}{if (tom_is_sort_Instrucao(((Object)i))) {if (tom_is_fun_sym_SeqInstrucao((( gram.i.types.Instrucao )(( gram.i.types.Instrucao )((Object)i))))) {if (!( ( tom_is_empty_SeqInstrucao_Instrucao((( gram.i.types.Instrucao )((Object)i))) || tom_equal_term_Instrucao((( gram.i.types.Instrucao )((Object)i)), tom_empty_list_SeqInstrucao()) ) )) {


        String genInst = compileAnnotInstrucao(((tom_is_fun_sym_SeqInstrucao((( gram.i.types.Instrucao )((Object)i))))?(tom_get_head_SeqInstrucao_Instrucao((( gram.i.types.Instrucao )((Object)i)))):((( gram.i.types.Instrucao )((Object)i)))),numInstrucao);
        String seqInst = genInst.concat(compileAnnotInstrucao(((tom_is_fun_sym_SeqInstrucao((( gram.i.types.Instrucao )((Object)i))))?(tom_get_tail_SeqInstrucao_Instrucao((( gram.i.types.Instrucao )((Object)i)))):(tom_empty_list_SeqInstrucao())),numInstrucao));

        return seqInst;
      }}}}}

    return "";
  }

  private String compileArguments(String functionName, Argumentos args) {
    {{if (tom_is_sort_Argumentos(((Object)args))) {if (tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )(( gram.i.types.Argumentos )((Object)args))))) {if (!( ( tom_is_empty_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)args))) || tom_equal_term_Argumentos((( gram.i.types.Argumentos )((Object)args)), tom_empty_list_ListaArgumentos()) ) )) {

        return compileArguments(functionName, ((tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )((Object)args))))?(tom_get_head_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)args)))):((( gram.i.types.Argumentos )((Object)args))))) + compileArguments(functionName, ((tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )((Object)args))))?(tom_get_tail_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)args)))):(tom_empty_list_ListaArgumentos())));
      }}}}{if (tom_is_sort_Argumentos(((Object)args))) {if (tom_is_sort_Argumentos((( gram.i.types.Argumentos )((Object)args)))) {if (tom_is_fun_sym_Argumento((( gram.i.types.Argumentos )(( gram.i.types.Argumentos )((Object)args))))) {

        int actualMemAddress = memAdress;
        memAdress++;
        int sizeAddress = 1;

        String prefix = functionName + "_";
        String declaration = "Decl \"" + prefix + tom_get_slot_Argumento_Id((( gram.i.types.Argumentos )((Object)args)))+ "\" " + actualMemAddress + " " +  sizeAddress + ",";

        return declaration;
      }}}}}

    return "";
  }

  public String compileAnnotDeclaracoes(Declaracoes decl, DefTipo tipo, NumToInt numInstrucao) {
    {{if (tom_is_sort_Declaracoes(((Object)decl))) {if (tom_is_fun_sym_ListaDecl((( gram.i.types.Declaracoes )(( gram.i.types.Declaracoes )((Object)decl))))) {if (!( ( tom_is_empty_ListaDecl_Declaracoes((( gram.i.types.Declaracoes )((Object)decl))) || tom_equal_term_Declaracoes((( gram.i.types.Declaracoes )((Object)decl)), tom_empty_list_ListaDecl()) ) )) {

        String gen = compileAnnotDeclaracoes(((tom_is_fun_sym_ListaDecl((( gram.i.types.Declaracoes )((Object)decl))))?(tom_get_head_ListaDecl_Declaracoes((( gram.i.types.Declaracoes )((Object)decl)))):((( gram.i.types.Declaracoes )((Object)decl)))),tipo,numInstrucao);
        String gen2 = compileAnnotDeclaracoes(((tom_is_fun_sym_ListaDecl((( gram.i.types.Declaracoes )((Object)decl))))?(tom_get_tail_ListaDecl_Declaracoes((( gram.i.types.Declaracoes )((Object)decl)))):(tom_empty_list_ListaDecl())),tipo,numInstrucao);

        return gen + gen2;
      }}}}{if (tom_is_sort_Declaracoes(((Object)decl))) {if (tom_is_sort_Declaracoes((( gram.i.types.Declaracoes )((Object)decl)))) {if (tom_is_fun_sym_Decl((( gram.i.types.Declaracoes )(( gram.i.types.Declaracoes )((Object)decl))))) { String  tom_id=tom_get_slot_Decl_Id((( gram.i.types.Declaracoes )((Object)decl)));


        String genExp = compileAnnotExpressoes(tom_get_slot_Decl_Expressao((( gram.i.types.Declaracoes )((Object)decl))),numInstrucao);
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        String storeValue;
        if (genExp.equals(""))
          storeValue = "";
        else
          storeValue = "Pusha \"" + prefix + tom_id+ "\"," + genExp + "Store,";

        int actualMemAddress = memAdress;
        memAdress++;
        int sizeAddress = 1;

        {{if (tom_is_sort_DefTipo(((Object)tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tipo)))) {if (tom_is_fun_sym_DInt((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tipo))))) {
 return "Decl \"" + prefix + tom_id+ "\" " + actualMemAddress + " " +  sizeAddress + "," + storeValue; }}}}{if (tom_is_sort_DefTipo(((Object)tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tipo)))) {if (tom_is_fun_sym_DChar((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tipo))))) {
 return "Decl \"" + prefix + tom_id+ "\" " +  actualMemAddress + " " +  sizeAddress + "," + storeValue; }}}}{if (tom_is_sort_DefTipo(((Object)tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tipo)))) {if (tom_is_fun_sym_DBoolean((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tipo))))) {
 return "Decl \"" + prefix + tom_id+ "\" " +  actualMemAddress + " " +  sizeAddress + "," + storeValue; }}}}{if (tom_is_sort_DefTipo(((Object)tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tipo)))) {if (tom_is_fun_sym_DFloat((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tipo))))) {
 return "Decl \"" + prefix + tom_id+ "\" " +  actualMemAddress + " " +  sizeAddress + "," + storeValue; }}}}{if (tom_is_sort_DefTipo(((Object)tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tipo)))) {if (tom_is_fun_sym_DVoid((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tipo))))) {
 return "Decl \"" + prefix + tom_id+ "\" " +  actualMemAddress + " " +  sizeAddress + "," + storeValue; }}}}}

        return "";
      }}}}}

    return "";
  }

  private String compileAnnotExpressoes(Expressao e, NumToInt numInstrucao) {
    {{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_ExpNum((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) { gram.i.types.OpNum  tom_op=tom_get_slot_ExpNum_op((( gram.i.types.Expressao )((Object)e)));

        String genExp1 = compileAnnotExpressoes(tom_get_slot_ExpNum_Exp1((( gram.i.types.Expressao )((Object)e))),numInstrucao);
        String genExp2 = compileAnnotExpressoes(tom_get_slot_ExpNum_Exp2((( gram.i.types.Expressao )((Object)e))),numInstrucao);

        {{if (tom_is_sort_OpNum(((Object)tom_op))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom_op)))) {if (tom_is_fun_sym_Mais((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom_op))))) {
 return genExp1 + genExp2 + "Add,"; }}}}{if (tom_is_sort_OpNum(((Object)tom_op))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom_op)))) {if (tom_is_fun_sym_Vezes((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom_op))))) {
 return genExp1 + genExp2 + "Mul,"; }}}}{if (tom_is_sort_OpNum(((Object)tom_op))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom_op)))) {if (tom_is_fun_sym_Divide((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom_op))))) {
 return genExp1 + genExp2 + "Div,"; }}}}{if (tom_is_sort_OpNum(((Object)tom_op))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom_op)))) {if (tom_is_fun_sym_Menos((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom_op))))) {
 return genExp1 + genExp2 + "Sub,"; }}}}{if (tom_is_sort_OpNum(((Object)tom_op))) {if (tom_is_sort_OpNum((( gram.i.types.OpNum )((Object)tom_op)))) {if (tom_is_fun_sym_Mod((( gram.i.types.OpNum )(( gram.i.types.OpNum )((Object)tom_op))))) {
 return genExp1.concat(genExp2); }}}}}

        return "";
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Id((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        return "Pusha \"" + prefix + tom_get_slot_Id_Id((( gram.i.types.Expressao )((Object)e)))+ "\",Load,"; 
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Pos((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 return compileAnnotExpressoes(tom_get_slot_Pos_Expressao((( gram.i.types.Expressao )((Object)e))),numInstrucao); }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Neg((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 return compileAnnotExpressoes(tom_get_slot_Neg_Expressao((( gram.i.types.Expressao )((Object)e))),numInstrucao); }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Nao((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 
        String genExp = compileAnnotExpressoes(tom_get_slot_Nao_Expressao((( gram.i.types.Expressao )((Object)e))),numInstrucao);
        return genExp + "Not,";
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Call((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) { String  tom_id=tom_get_slot_Call_Id((( gram.i.types.Expressao )((Object)e)));

 
        Argumentos argumentos = functionSignatures.get(tom_id);
        String prefix = "f:";
        String loadReturn = callReturnNeeded ? "Pusha \"" + prefix + tom_id+ "\",Load," : "";
        String genCallParameters = compileCallParameters(tom_id, argumentos, tom_get_slot_Call_Parametros((( gram.i.types.Expressao )((Object)e))), numInstrucao);
        String call = "Call \"" + prefix + tom_id+ "\",";
        return genCallParameters + call + loadReturn;
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_IncAntes((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) { gram.i.types.OpInc  tom_opInc=tom_get_slot_IncAntes_OpInc((( gram.i.types.Expressao )((Object)e))); String  tom_id=tom_get_slot_IncAntes_Id((( gram.i.types.Expressao )((Object)e)));

 
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        {{if (tom_is_sort_OpInc(((Object)tom_opInc))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom_opInc)))) {if (tom_is_fun_sym_Inc((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom_opInc))))) {
 return "Pusha \"" + prefix + tom_id+ "\",Inc"; }}}}{if (tom_is_sort_OpInc(((Object)tom_opInc))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom_opInc)))) {if (tom_is_fun_sym_Dec((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom_opInc))))) {
 return "Pusha \"" + prefix + tom_id+ "\",Dec"; }}}}}

        return tom_id;
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_IncDepois((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) { gram.i.types.OpInc  tom_opInc=tom_get_slot_IncDepois_OpInc((( gram.i.types.Expressao )((Object)e))); String  tom_id=tom_get_slot_IncDepois_Id((( gram.i.types.Expressao )((Object)e)));

 
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        {{if (tom_is_sort_OpInc(((Object)tom_opInc))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom_opInc)))) {if (tom_is_fun_sym_Inc((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom_opInc))))) {
 return "Pusha \"" + prefix + tom_id+ "\",Inc,"; }}}}{if (tom_is_sort_OpInc(((Object)tom_opInc))) {if (tom_is_sort_OpInc((( gram.i.types.OpInc )((Object)tom_opInc)))) {if (tom_is_fun_sym_Dec((( gram.i.types.OpInc )(( gram.i.types.OpInc )((Object)tom_opInc))))) {
 return "Pusha \"" + prefix + tom_id+ "\",Dec,"; }}}}}

        return tom_id;
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Condicional((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {


        String genCondicao = compileAnnotExpressoes(tom_get_slot_Condicional_Condicao((( gram.i.types.Expressao )((Object)e))),numInstrucao);
        String genExp1 = compileAnnotExpressoes(tom_get_slot_Condicional_Exp1((( gram.i.types.Expressao )((Object)e))),numInstrucao);
        String genExp2 = compileAnnotExpressoes(tom_get_slot_Condicional_Exp2((( gram.i.types.Expressao )((Object)e))),numInstrucao);

        return genCondicao.concat(genExp1).concat(genExp2);
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Int((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 return "Pushi "+ tom_get_slot_Int_Int((( gram.i.types.Expressao )((Object)e)))+ ","; }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Char((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 return "Pushc '" + tom_get_slot_Char_Char((( gram.i.types.Expressao )((Object)e))).charAt(0) + "',"; }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_True((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 return "Pushb true,"; }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_False((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 return "Pushb false,"; }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Float((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 return "Pushf "+tom_get_slot_Float_num((( gram.i.types.Expressao )((Object)e)))+ ","; }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Ou((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {


        String genCond1 = compileAnnotExpressoes(tom_get_slot_Ou_Cond1((( gram.i.types.Expressao )((Object)e))),numInstrucao);
        String genCond2 = compileAnnotExpressoes(tom_get_slot_Ou_Cond2((( gram.i.types.Expressao )((Object)e))),numInstrucao);

        return genCond1 + genCond2 + "Or,";
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_E((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {


        String genCond1 = compileAnnotExpressoes(tom_get_slot_E_Cond1((( gram.i.types.Expressao )((Object)e))),numInstrucao);
        String genCond2 = compileAnnotExpressoes(tom_get_slot_E_Cond2((( gram.i.types.Expressao )((Object)e))),numInstrucao);

        return genCond1 + genCond2 + "And,";
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Comp((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) { gram.i.types.OpComp  tom_opComp=tom_get_slot_Comp_OpComp((( gram.i.types.Expressao )((Object)e)));


        String genExp1 = compileAnnotExpressoes(tom_get_slot_Comp_Exp1((( gram.i.types.Expressao )((Object)e))),numInstrucao);
        String genExp2 = compileAnnotExpressoes(tom_get_slot_Comp_Exp2((( gram.i.types.Expressao )((Object)e))),numInstrucao);

        {{if (tom_is_sort_OpComp(((Object)tom_opComp))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom_opComp)))) {if (tom_is_fun_sym_Maior((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom_opComp))))) {
 return genExp1 + genExp2 + "Gt,"; }}}}{if (tom_is_sort_OpComp(((Object)tom_opComp))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom_opComp)))) {if (tom_is_fun_sym_Menor((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom_opComp))))) {
 return genExp1 + genExp2 + "Lt,"; }}}}{if (tom_is_sort_OpComp(((Object)tom_opComp))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom_opComp)))) {if (tom_is_fun_sym_MaiorQ((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom_opComp))))) {
 return genExp1 + genExp2 + "GoEq,"; }}}}{if (tom_is_sort_OpComp(((Object)tom_opComp))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom_opComp)))) {if (tom_is_fun_sym_MenorQ((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom_opComp))))) {
 return genExp1 + genExp2 + "LoEq,"; }}}}{if (tom_is_sort_OpComp(((Object)tom_opComp))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom_opComp)))) {if (tom_is_fun_sym_Dif((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom_opComp))))) {
 return genExp1 + genExp2 + "Neq,"; }}}}{if (tom_is_sort_OpComp(((Object)tom_opComp))) {if (tom_is_sort_OpComp((( gram.i.types.OpComp )((Object)tom_opComp)))) {if (tom_is_fun_sym_Igual((( gram.i.types.OpComp )(( gram.i.types.OpComp )((Object)tom_opComp))))) {
 return genExp1 + genExp2 + "Eq,"; }}}}}

      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Input((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) { gram.i.types.DefTipo  tom_tipo=tom_get_slot_Input_Tipo((( gram.i.types.Expressao )((Object)e)));{{if (tom_is_sort_DefTipo(((Object)tom_tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tom_tipo)))) {if (tom_is_fun_sym_DInt((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tom_tipo))))) {



 return "IIn int,"; }}}}{if (tom_is_sort_DefTipo(((Object)tom_tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tom_tipo)))) {if (tom_is_fun_sym_DChar((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tom_tipo))))) {
 return "IIn char,"; }}}}{if (tom_is_sort_DefTipo(((Object)tom_tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tom_tipo)))) {if (tom_is_fun_sym_DBoolean((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tom_tipo))))) {
 return "IIn boolean,"; }}}}{if (tom_is_sort_DefTipo(((Object)tom_tipo))) {if (tom_is_sort_DefTipo((( gram.i.types.DefTipo )((Object)tom_tipo)))) {if (tom_is_fun_sym_DFloat((( gram.i.types.DefTipo )(( gram.i.types.DefTipo )((Object)tom_tipo))))) {
 return "IIn float,"; }}}}}

      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Print((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {


        String genExp = compileAnnotExpressoes(tom_get_slot_Print_Expressao((( gram.i.types.Expressao )((Object)e))),numInstrucao);

        return genExp + "IOut,";
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_fun_sym_Expressoes((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {if (!( ( tom_is_empty_Expressoes_Expressao((( gram.i.types.Expressao )((Object)e))) || tom_equal_term_Expressao((( gram.i.types.Expressao )((Object)e)), tom_empty_list_Expressoes()) ) )) {


        String genExp = compileAnnotExpressoes(((tom_is_fun_sym_Expressoes((( gram.i.types.Expressao )((Object)e))))?(tom_get_head_Expressoes_Expressao((( gram.i.types.Expressao )((Object)e)))):((( gram.i.types.Expressao )((Object)e)))),numInstrucao);
        String exps = genExp.concat(compileAnnotExpressoes(((tom_is_fun_sym_Expressoes((( gram.i.types.Expressao )((Object)e))))?(tom_get_tail_Expressoes_Expressao((( gram.i.types.Expressao )((Object)e)))):(tom_empty_list_Expressoes())),numInstrucao));

        return exps;
      }}}}{if (tom_is_sort_Expressao(((Object)e))) {if (tom_is_sort_Expressao((( gram.i.types.Expressao )((Object)e)))) {if (tom_is_fun_sym_Empty((( gram.i.types.Expressao )(( gram.i.types.Expressao )((Object)e))))) {

 return ""; }}}}}

    return "";
  }

  private String compileCallParameters(String functionName, Argumentos argumentos, Parametros parametros, NumToInt numInstrucao) {
    {{if (tom_is_sort_Parametros(((Object)parametros))) {if (tom_is_fun_sym_ListaParametros((( gram.i.types.Parametros )(( gram.i.types.Parametros )((Object)parametros))))) {if (!( ( tom_is_empty_ListaParametros_Parametros((( gram.i.types.Parametros )((Object)parametros))) || tom_equal_term_Parametros((( gram.i.types.Parametros )((Object)parametros)), tom_empty_list_ListaParametros()) ) )) {if (tom_is_sort_Argumentos(((Object)argumentos))) {if (tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )(( gram.i.types.Argumentos )((Object)argumentos))))) {if (!( ( tom_is_empty_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)argumentos))) || tom_equal_term_Argumentos((( gram.i.types.Argumentos )((Object)argumentos)), tom_empty_list_ListaArgumentos()) ) )) {

        return compileCallParameters(functionName, ((tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )((Object)argumentos))))?(tom_get_head_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)argumentos)))):((( gram.i.types.Argumentos )((Object)argumentos)))), ((tom_is_fun_sym_ListaParametros((( gram.i.types.Parametros )((Object)parametros))))?(tom_get_head_ListaParametros_Parametros((( gram.i.types.Parametros )((Object)parametros)))):((( gram.i.types.Parametros )((Object)parametros)))), numInstrucao) + compileCallParameters(functionName, ((tom_is_fun_sym_ListaArgumentos((( gram.i.types.Argumentos )((Object)argumentos))))?(tom_get_tail_ListaArgumentos_Argumentos((( gram.i.types.Argumentos )((Object)argumentos)))):(tom_empty_list_ListaArgumentos())), ((tom_is_fun_sym_ListaParametros((( gram.i.types.Parametros )((Object)parametros))))?(tom_get_tail_ListaParametros_Parametros((( gram.i.types.Parametros )((Object)parametros)))):(tom_empty_list_ListaParametros())), numInstrucao);
      }}}}}}}{if (tom_is_sort_Parametros(((Object)parametros))) {if (tom_is_sort_Parametros((( gram.i.types.Parametros )((Object)parametros)))) {if (tom_is_fun_sym_Parametro((( gram.i.types.Parametros )(( gram.i.types.Parametros )((Object)parametros))))) {if (tom_is_sort_Argumentos(((Object)argumentos))) {if (tom_is_sort_Argumentos((( gram.i.types.Argumentos )((Object)argumentos)))) {if (tom_is_fun_sym_Argumento((( gram.i.types.Argumentos )(( gram.i.types.Argumentos )((Object)argumentos))))) {

        String genExp = compileAnnotExpressoes(tom_get_slot_Parametro_Expressao((( gram.i.types.Parametros )((Object)parametros))),numInstrucao);
        String prefix = functionName + "_";
        return "Pusha \"" + prefix + tom_get_slot_Argumento_Id((( gram.i.types.Argumentos )((Object)argumentos)))+ "\"," + genExp + "Store,";
      }}}}}}}}

    return "";
  }

  private String genAnnotation(int i) {
    if (i == 1) {
      return "Pushi "+i+",IOut,";
    }
    else {
      return "Pushc ',',IOut,Pushi "+i+",IOut,";
    }
  }

  private static boolean parseFile(String filename, TreeSet<Integer> blocos) { 
    try { 
      BufferedReader br = new BufferedReader( new FileReader(filename) ); 
      String line = ""; 
      StringTokenizer token = null; 

      while((line = br.readLine()) != null) { 
        token = new StringTokenizer(line, ","); 

        while(token.hasMoreTokens()) { 
          String tokenS = token.nextToken();
          blocos.add(Integer.parseInt(tokenS));
        } 
      } 

      return true; 
    } catch(Exception e) { 
      return false; 
    }
  }
}

class NumToInt{
  private int num;

  public NumToInt(int num) {
    this.num = num;
  }

  public NumToInt(){
    num = 0;
  }

  public int inc(){
    return num++;
  }

  public int get() {
    return num;
  }
}

