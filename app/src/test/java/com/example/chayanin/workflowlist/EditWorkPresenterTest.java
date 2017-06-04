package com.example.chayanin.workflowlist;

import com.example.chayanin.workflowlist.EditWork.EditWorkPresenter;
import com.example.chayanin.workflowlist.EditWork.EditWorkView;
import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.Model.WorkRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EditWorkPresenterTest {

    private EditWorkPresenter presenter;
    private EditWorkView view;

    @Before
    public void setUp() {
        WorkRepository repository = WorkRepository.getInstance();
        repository.getWorks().clear();
        repository.getWorks().add(new Work("W1", new ArrayList<Process>(), "04/04/2018", "07:07"));
        repository.getWorks().get(0).addNewProcess(new Process("P1"));
        repository.getWorks().add(new Work("W2", new ArrayList<Process>(), "15/07/2018", "16:30"));
        repository.getWorks().get(1).addNewProcess(new Process("P2_1"));
        repository.getWorks().get(1).addNewProcess(new Process("P2_2"));
        repository.getWorks().add(new Work("W3", new ArrayList<Process>(), "30/09/2017", "00:04"));
        repository.getWorks().get(2).addNewProcess(new Process("P3"));
    }

    @Test
    public void shouldSuccessEditWork() {
        presenter = new EditWorkPresenter(view, 1);
        assertEquals("W2", presenter.getWork().getTopic());
        presenter.getWork().setTopic("New W2");
        assertEquals("New W2", presenter.getWork().getTopic());
    }

    @Test
    public void shouldCorrectCheckingDateAndTime() {
        presenter = new EditWorkPresenter(view, 0);
        assertEquals(true, presenter.isCorrectDate(2017, 5, 31, 15, 30));
        assertEquals(false, presenter.isCorrectDate(2017, 5, 32, 15, 30));
    }

    @Test
    public void shouldCorrectCheckingLeapYear() {
        presenter = new EditWorkPresenter(view, 0);
        assertEquals(true, presenter.isLeapYear(2020));
        assertEquals(false, presenter.isLeapYear(2021));
    }

}
