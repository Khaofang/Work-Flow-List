package com.example.chayanin.workflowlist;

import com.example.chayanin.workflowlist.AddWork.AddWorkPresenter;
import com.example.chayanin.workflowlist.AddWork.AddWorkView;
import com.example.chayanin.workflowlist.EditWork.EditWorkPresenter;
import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.Model.WorkRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AddWorkPresenterTest {

    private AddWorkPresenter presenter;
    private AddWorkView view;

    @Before
    public void setUp() {
        presenter = new AddWorkPresenter(view);
    }

    @Test
    public void shouldSuccessAddWorkThorughPresenter() {
        WorkRepository repository = WorkRepository.getInstance();
        repository.getWorks().clear();
        Work w1 = new Work("W1", new ArrayList<Process>(), "01/01/2018", "09:00");
        w1.addNewProcess(new Process("P1"));
        w1.addNewProcess(new Process("P2"));
        w1.addNewProcess(new Process("P3"));
        presenter.addNewWork(w1);
        assertEquals(1, repository.getWorks().size());
        assertEquals(3, repository.getWorks().get(0).getProcesses().size());

        Work w2 = new Work("W2", new ArrayList<Process>(), "01/01/2018", "09:00");
        presenter.addNewWork(w2);
        assertEquals(2, repository.getWorks().size());
        assertEquals(0, repository.getWorks().get(1).getProcesses().size());
    }

    @Test
    public void shouldCorrectCheckingDateAndTime() {
        presenter = new AddWorkPresenter(view);
        assertEquals(true, presenter.isCorrectDate(2017, 2, 28, 15, 30));
        assertEquals(false, presenter.isCorrectDate(2017, 2, 29, 25, 30));
    }

    @Test
    public void shouldCorrectCheckingLeapYear() {
        presenter = new AddWorkPresenter(view);
        assertEquals(true, presenter.isLeapYear(2012));
        assertEquals(false, presenter.isLeapYear(2017));
    }

}
