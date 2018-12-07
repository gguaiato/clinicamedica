package jsf;

import dao.entities.ConsultaDiagnostico;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.session.ConsultaDiagnosticoFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("consultaDiagnosticoController")
@SessionScoped
public class ConsultaDiagnosticoController implements Serializable {

    private ConsultaDiagnostico current;
    private DataModel items = null;
    @EJB
    private jpa.session.ConsultaDiagnosticoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ConsultaDiagnosticoController() {
    }

    public ConsultaDiagnostico getSelected() {
        if (current == null) {
            current = new ConsultaDiagnostico();
            current.setConsultaDiagnosticoPK(new dao.entities.ConsultaDiagnosticoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ConsultaDiagnosticoFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ConsultaDiagnostico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ConsultaDiagnostico();
        current.setConsultaDiagnosticoPK(new dao.entities.ConsultaDiagnosticoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getConsultaDiagnosticoPK().setIdDiagnostico(current.getDoença().getIdDoenca());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("ConsultaDiagnosticoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ConsultaDiagnostico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getConsultaDiagnosticoPK().setIdDiagnostico(current.getDoença().getIdDoenca());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("ConsultaDiagnosticoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ConsultaDiagnostico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("ConsultaDiagnosticoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public ConsultaDiagnostico getConsultaDiagnostico(dao.entities.ConsultaDiagnosticoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ConsultaDiagnostico.class)
    public static class ConsultaDiagnosticoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConsultaDiagnosticoController controller = (ConsultaDiagnosticoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "consultaDiagnosticoController");
            return controller.getConsultaDiagnostico(getKey(value));
        }

        dao.entities.ConsultaDiagnosticoPK getKey(String value) {
            dao.entities.ConsultaDiagnosticoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new dao.entities.ConsultaDiagnosticoPK();
            key.setIdConsulta(Integer.parseInt(values[0]));
            key.setIdDiagnostico(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(dao.entities.ConsultaDiagnosticoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdConsulta());
            sb.append(SEPARATOR);
            sb.append(value.getIdDiagnostico());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ConsultaDiagnostico) {
                ConsultaDiagnostico o = (ConsultaDiagnostico) object;
                return getStringKey(o.getConsultaDiagnosticoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ConsultaDiagnostico.class.getName());
            }
        }

    }

}
