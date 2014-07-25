package jsf;

import com.hartbook.KanjiElem;
import com.hartbook.ReadingElem;
import com.hartbook.SenseContainer;
import com.hartbook.SenseElem;
import com.hartbook.Serializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import jpa.entities.JERefs;
import jpa.session.JERefsFacade;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;

@Named("jERefsController")
@SessionScoped
public class JERefsController implements Serializable
{

    @EJB
    private jpa.session.JERefsFacade ejbFacade;
    
    private String kana = "reading";
    private String kanji = "kanji";
    private String english = "english";
    
    private List<JERefs> items = null;
    private List<SenseElem> senses; 
    private JERefs selected;
    
    //private final Serializer ser;
    private KanjiElem kElem;
    private ReadingElem rElem;
    private SenseElem sElem;
    private SenseContainer sCont;

    public JERefsController()
    {
        System.out.println("new JERefsController");
        //ser = new Serializer();
    }

    public String getKana()
    {
        return kana;
    }

    public void setKana(String kana)
    {
        this.kana = kana;
    }

    public String getKanji()
    {
        return kanji;
    }

    public void setKanji(String kanji)
    {
        this.kanji = kanji;
    }

    public String getEnglish()
    {
        return english;
    }

    public void setEnglish(String english)
    {
        this.english = english;
    }
    
    private void deserializeKanji(byte[] bytes) throws IOException, ClassNotFoundException
    {
        kElem = new KanjiElem();
        //kElem = (KanjiElem) ser.deserialize(bytes);
        //return kElem;
    }
    
    private void deserializeRead(byte[] bytes) throws IOException, ClassNotFoundException
    {
        rElem = new ReadingElem();
        //rElem = (ReadingElem) ser.deserialize(bytes);
        //return rElem;
    }

    private void deserializeSCont(byte[] bytes) throws IOException, ClassNotFoundException
    {
        sCont = new SenseContainer();
        //sCont = (SenseContainer) ser.deserialize(bytes);
        //return sCont;
    }
    
    private void deserializeAll() throws IOException, ClassNotFoundException
    {
        deserializeKanji(selected.getKanji());
        deserializeRead(selected.getKana());
        deserializeSCont(selected.getSenses());
        System.out.println("JERefsController.deserializeAll");
    }
    
    private void setAll() throws IOException, ClassNotFoundException
    {
        System.out.println("JERefsController.setAll");
        deserializeAll();
        senses = sCont.getsContain();
        List<String> sList = new ArrayList<>();
        senses.stream().forEach((_item) ->
        {
            sList.add(listToString(sElem.getMeaningList()));
        });
        
        this.kana = listToString(rElem.getRebs());
        this.kanji = listToString(kElem.getKebs());
        this.english = listToString(sList);
    }
    
    
    // Creates a CSV of a list of either the reading(s) or the meaning(s) of a Kanji Entry
    private String listToString(List<String> list)
    {
        // Using this aggregate stream as Iterator should only be used for removing
        String listCat = list.stream().collect(Collectors.joining("|"));
       
        return listCat;
    }
    
    public JERefs getSelected()
    {
        System.out.println("JERefsController.getSelected");
        return selected;
    }

    public void setSelected(JERefs selected)
    {
        this.selected = selected;
    }

    protected void setEmbeddableKeys()
    {
    }

    protected void initializeEmbeddableKey()
    {
    }

    private JERefsFacade getFacade()
    {
        System.out.println("JERefsController.getFacade");
        return ejbFacade;
    }

    public JERefs prepareCreate() throws IOException, ClassNotFoundException
    {
        System.out.println("JERefsController.prepareCreate");
        selected = new JERefs();
        initializeEmbeddableKey();
        //setAll();
        return selected;
    }

    public void create()
    {
        System.out.println("JERefsController.create");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("JERefsCreated"));
        if (!JsfUtil.isValidationFailed())
        {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update()
    {
        System.out.println("JERefsController.update");
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("JERefsUpdated"));
    }

    public void destroy()
    {
        System.out.println("JERefsController.destroy");
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("JERefsDeleted"));
        if (!JsfUtil.isValidationFailed())
        {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<JERefs> getItems()
    {
        System.out.println("JERefsController.getItems");
        if (items == null)
        {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage)
    {
        System.out.println("JERefsController.persist");
        if (selected != null)
        {
            setEmbeddableKeys();
            try
            {
                if (persistAction != PersistAction.DELETE)
                {
                    getFacade().edit(selected);
                } else
                {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex)
            {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null)
                {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0)
                {
                    JsfUtil.addErrorMessage(msg);
                } else
                {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex)
            {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public JERefs getJERefs(java.lang.Integer id)
    {
        System.out.println("JERefsController.getJERefs");
        return getFacade().find(id);
    }

    public List<JERefs> getItemsAvailableSelectMany()
    {
        System.out.println("JERefsController.getItemsAvailableSelectMany");
        return getFacade().findAll();
    }

    public List<JERefs> getItemsAvailableSelectOne()
    {
        System.out.println("JERefsController.getItemsAvailableSelectOne");
        return getFacade().findAll();
    }

    @FacesConverter(forClass = JERefs.class)
    public static class JERefsControllerConverter implements Converter
    {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value)
        {
            if (value == null || value.length() == 0)
            {
                return null;
            }
            JERefsController controller = (JERefsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "jERefsController");
            return controller.getJERefs(getKey(value));
        }

        java.lang.Integer getKey(String value)
        {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object)
        {
            if (object == null)
            {
                return null;
            }
            if (object instanceof JERefs)
            {
                JERefs o = (JERefs) object;
                return getStringKey(o.getId());
            } else
            {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]
                {
                    object, object.getClass().getName(), JERefs.class.getName()
                });
                return null;
            }
        }

    }

}
