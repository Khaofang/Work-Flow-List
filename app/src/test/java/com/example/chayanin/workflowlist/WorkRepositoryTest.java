package com.example.chayanin.workflowlist;

import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.Model.WorkRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WorkRepositoryTest {

    private WorkRepository repository;

    @Before
    public void setUp() {
        repository = WorkRepository.getInstance();
    }

    @Test
    public void shouldSuccessAddWork() {
        repository.getWorks().clear();
        Process p1 = new Process("P1", true);
        Process p2 = new Process("P2");
        Process p3 = new Process("P3", false);
        List<Process> processes = new ArrayList<Process>();
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        Work w = new Work("W1", processes, "05/06/2017", "23:59");
        repository.addWork(w);
        assertEquals(1, repository.getWorks().size());

        Work w1 = repository.getWorks().get(0);
        assertEquals("W1", w1.getTopic());
        assertEquals(3, w1.getProcesses().size());
        assertEquals("05/06/2017", w1.getDeadlineDate());
        assertEquals("23:59", w1.getDeadlineTime());
    }

    @Test
    public void shouldSuccessDeleteWork() {
        repository.getWorks().clear();
        repository.addWork(new Work("W1", new ArrayList<Process>(), "01/01/2018", "11:11"));
        repository.addWork(new Work("W2", new ArrayList<Process>(), "29/07/2017", "03:16"));
        repository.addWork(new Work("W3", new ArrayList<Process>(), "04/06/2018", "19:51"));
        assertEquals(3, repository.getWorks().size());

        repository.getWorks().remove(1);
        assertEquals(2, repository.getWorks().size());

        Work w1 = repository.getWorks().get(0);
        assertEquals("W1", w1.getTopic());
        assertEquals(0, w1.getProcesses().size());
        assertEquals("01/01/2018", w1.getDeadlineDate());
        assertEquals("11:11", w1.getDeadlineTime());

        Work w3 = repository.getWorks().get(1);
        assertEquals("W3", w3.getTopic());
        assertEquals(0, w3.getProcesses().size());
        assertEquals("04/06/2018", w3.getDeadlineDate());
        assertEquals("19:51", w3.getDeadlineTime());
    }

    @Test
    public void shouldSuccessEditWork() {
        repository.getWorks().clear();
        repository.addWork(new Work("W1", new ArrayList<Process>(), "01/01/2018", "00:00"));

        Work w1 = repository.getWorks().get(0);
        assertEquals("W1", w1.getTopic());
        assertEquals(0, w1.getProcesses().size());
        assertEquals("01/01/2018", w1.getDeadlineDate());
        assertEquals("00:00", w1.getDeadlineTime());

        w1.setTopic("W1_v1");
        w1.getProcesses().add(new Process("P1"));
        w1.setDeadlineDate("05/06/2017");
        w1.setDeadlineTime("06:00");
        assertEquals("W1_v1", w1.getTopic());
        assertEquals(1, w1.getProcesses().size());
        assertEquals("05/06/2017", w1.getDeadlineDate());
        assertEquals("06:00", w1.getDeadlineTime());
    }

    @Test
    public void shouldCorrectCalculateProcessCompleted() {
        repository.getWorks().clear();
        repository.addWork(new Work("W1", new ArrayList<Process>(), "01/01/2018", "00:00"));
        List<Process> processes = repository.getWorks().get(0).getProcesses();
        processes.add(new Process("P1", true));
        processes.add(new Process("P2", false));
        processes.add(new Process("P3", true));
        processes.add(new Process("P4", true));
        processes.add(new Process("P5", true));
        processes.add(new Process("P6", false));
        assertEquals(4, repository.getWorks().get(0).getNumProcessesFinished());
    }

    @Test
    public void shouldSuccesChangeProcessStatus() {
        repository.getWorks().clear();
        repository.addWork(new Work("W1", new ArrayList<Process>(), "01/01/2018", "00:00"));
        List<Process> processes = repository.getWorks().get(0).getProcesses();
        processes.add(new Process("P1", true));
        processes.add(new Process("P2", false));
        processes.add(new Process("P3", true));
        processes.add(new Process("P4", false));
        Process p1 = processes.get(0);
        Process p2 = processes.get(1);
        Process p3 = processes.get(2);
        Process p4 = processes.get(3);
        p1.notFinish();
        p2.finish();
        p3.finish();
        p4.notFinish();
        assertEquals(2, repository.getWorks().get(0).getNumProcessesFinished());
        assertEquals(false, repository.getWorks().get(0).getProcesses().get(0).isFinish());
        assertEquals(true, repository.getWorks().get(0).getProcesses().get(1).isFinish());
        assertEquals(true, repository.getWorks().get(0).getProcesses().get(2).isFinish());
        assertEquals(false, repository.getWorks().get(0).getProcesses().get(3).isFinish());
    }

}
