package org.cdisource.springapp.web;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.cdisource.springapp.Task;
import org.cdisource.springapp.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@RequestMapping("/tasks")
@Controller
public class TaskController {

	@Autowired
	TaskRepository repo;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Task task, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {

		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("task", task);
			return "tasks/create";
		}

		uiModel.asMap().clear();
		task = repo.merge(task);

		return "redirect:/tasks/"
				+ encodeUrlPathSegment(task.getId().toString(),
						httpServletRequest);

	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		uiModel.addAttribute("task", new Task());
		return "tasks/create";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("task", repo.findTask(id));
		uiModel.addAttribute("itemId", id);
		return "tasks/show";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute("tasks", repo.findTaskEntries(page == null ? 0
					: (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) repo.countTasks() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("tasks", repo.findAllTasks());
		}
		return "tasks/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Task task, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("task", task);
			return "tasks/update";
		}
		uiModel.asMap().clear();
		repo.merge(task);
		return "redirect:/tasks/"
				+ encodeUrlPathSegment(task.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("task", repo.findTask(id));
		return "tasks/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		repo.remove(repo.findTask(id));
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/tasks";
	}

	@ModelAttribute("tasks")
	public Collection<Task> populateTasks() {
		return repo.findAllTasks();
	}

	String encodeUrlPathSegment(String pathSegment,
			HttpServletRequest httpServletRequest) {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException uee) {
		}
		return pathSegment;
	}
}
