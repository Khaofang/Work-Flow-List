package com.example.chayanin.workflowlist;

import com.example.chayanin.workflowlist.EditWork.EditWorkPresenter;
import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.Model.WorkRepository;
import com.example.chayanin.workflowlist.ViewWork.ViewWorkPresenter;
import com.example.chayanin.workflowlist.ViewWork.ViewWorkView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ViewWorkPresenterTest {

    ViewWorkPresenter presenter;
    ViewWorkView view;

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
        presenter = new ViewWorkPresenter(view, 1);
        assertEquals("W2", presenter.getWork().getTopic());
        assertEquals(2, presenter.getWork().getProcesses().size());
        assertEquals("15/07/2018", presenter.getWork().getDeadlineDate());
        assertEquals("16:30", presenter.getWork().getDeadlineTime());
    }

}
