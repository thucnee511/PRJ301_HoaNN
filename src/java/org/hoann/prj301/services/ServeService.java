package org.hoann.prj301.services;

import org.hoann.prj301.core.Generator;
import org.hoann.prj301.dao.ServiceDAO;
import org.hoann.prj301.repositories.service.ServiceDTO;

public class ServeService {

    private static ServeService instance;
    private static final ServiceDAO serviceDao = ServiceDAO.getInstance();

    private ServeService() {
    }

    public static ServeService getInstance() {
        return instance = instance == null ? new ServeService() : instance;
    }

    public boolean post(String name, double price) {
        String serviceId = Generator.generateUUID();
        boolean status = true;
        serviceDao.post(new ServiceDTO(serviceId, name, price, status));
        return true;
    }

    public boolean put(ServiceDTO service) {
        serviceDao.put(service);
        return true;
    }
}
