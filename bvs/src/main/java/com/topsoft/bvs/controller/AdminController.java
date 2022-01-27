package com.topsoft.bvs.controller;




import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.topsoft.bvs.controller.dto.CandidateRegDto;
import com.topsoft.bvs.controller.dto.CitizenRegistrationDto;
import com.topsoft.bvs.controller.dto.DelimRegDto;
import com.topsoft.bvs.controller.dto.ElectionDto;
import com.topsoft.bvs.controller.dto.PartyRegDto;
import com.topsoft.bvs.controller.dto.PostRegDto;
import com.topsoft.bvs.entity.Candidate;
import com.topsoft.bvs.entity.Citizen;
import com.topsoft.bvs.entity.Delimitation;
import com.topsoft.bvs.entity.Election;
import com.topsoft.bvs.entity.Party;
import com.topsoft.bvs.entity.Post;
import com.topsoft.bvs.entity.User;
import com.topsoft.bvs.service.CandidateService;
import com.topsoft.bvs.service.CitizenService;
import com.topsoft.bvs.service.DelimService;
import com.topsoft.bvs.service.ElectionService;
import com.topsoft.bvs.service.PartyService;
import com.topsoft.bvs.service.PostService;
import com.topsoft.bvs.service.UserService;




@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Value("${uploadDir}")
	private String uploadFolder;
	
	private final Logger log =LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CitizenService citizenService;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private DelimService delimService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private PostService postService;
	@Autowired
	private ElectionService electionService;
	@Autowired
	private UserService service;
	
	
	@RequestMapping(value="demo", method = RequestMethod.GET)
	public String demo() {
		return "demo/demo1";
	}
	@GetMapping("/dashboard")
	public String showDashboard(Model model, Principal principal) {
		List<Candidate>cands = candidateService.findall();
		List<Citizen>citizens = citizenService.findAll();
		User currentUser = service.findByUsername(principal.getName());
		
		model.addAttribute("candSize", cands.size());
		model.addAttribute("citizenSize", citizens.size());
		model.addAttribute("currentUser", currentUser);
		
	
		return "admin-dashboard";
	}
	@GetMapping("/electionPages/{pageNo}")
	public String electionPaginated(@PathVariable (value="pageNo") int pageNo, Model model,Principal principal) {
		User currentUser = service.findByUsername(principal.getName());
		List<Candidate>cands = candidateService.findall();
		List<Citizen>citizens = citizenService.findAll();
		int pageSize = 10;
		Page<Election> page = electionService.findPagination(pageNo, pageSize);
		List<Election> elections =page.getContent();
		model.addAttribute("election",new ElectionDto());
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("elections",elections);
		model.addAttribute("candSize", cands.size());
		model.addAttribute("citizenSize", citizens.size());
		model.addAttribute("currentUser", currentUser);
		return "create-election";
		
	}
	@GetMapping("/createElection")
	public String showElectionForm(Model model, Principal principal) {
		/*List<Candidate>cands = candidateService.findall();
		List<Citizen>citizens = citizenService.findAll();
		User currentUser = service.findByUsername(principal.getName());
		model.addAttribute("election",new ElectionDto());
		model.addAttribute("candSize", cands.size());
		model.addAttribute("citizenSize", citizens.size());
		model.addAttribute("currentUser", currentUser);*/
		return electionPaginated(1,model,principal);
	}
	@GetMapping("/addElection/{id}")
	public ModelAndView editElection(@PathVariable(name="id") Long id) {
		ModelAndView editView = new ModelAndView("");
		Delimitation delim = delimService.findPartyById(id);
		editView.addObject("delim",delim);
		return editView;
	} 
	@GetMapping("/deleteElection/{id}")
	public String deleteElection (@PathVariable(name="id") Long id) {
		electionService.deleteDelim(id);
		return "redirect:/admin/createElection";
	} 

	@PostMapping("/createElection")
	public String saveElection(@ModelAttribute("election") ElectionDto eDto) {
		electionService.createElection(eDto);
		return "redirect:/admin/createElection?success";
	}
	/*try {
		electionService.createElection(eDto);
		return "redirect:/admin/createElection?success";
	} catch (Exception e) {
		return "redirect:/admin/createElection?error";
	}	}*/
	@GetMapping("/citizenPages/{pageNo}")
	public String citizenPaginated(@PathVariable (value="pageNo") int pageNo, Model model,Principal principal) {
		User currentUser = service.findByUsername(principal.getName());
		int pageSize = 10;
		Page<Citizen> page = citizenService.findPagination(pageNo, pageSize);
		List<Citizen> delims =page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("delims",delims);
		model.addAttribute("delim",new Delimitation());
		model.addAttribute("currentUser", currentUser);
		return "search-citizen";
		
	}
	@GetMapping("/searchCitizen")
	public String searchCitizen(Model model, Principal principal) {
		return citizenPaginated(1,model,principal);
		
	}

	
	@GetMapping("/registerCitizen")
	public String showCitizenForm(Model model, Principal principal) {
	
		User currentUser = service.findByUsername(principal.getName());
		model.addAttribute("citizen",new CitizenRegistrationDto());
		model.addAttribute("currentUser", currentUser);
		return "add-citizen";
	}
	@PostMapping("/registerCitizen")
	public String registerCitizen(@ModelAttribute("citizen") CitizenRegistrationDto citizenRegistrationDto,
			HttpServletRequest request,final @RequestParam("image") MultipartFile file) {
		
		
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			String filename=file.getOriginalFilename();
			String filepath= Paths.get(uploadDirectory,filename).toString();
			
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("folder created");
					dir.mkdir();
				}
				//save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream
						( new File(filepath)));
				stream.write(file.getBytes());
				stream.close();
			}catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData =file.getBytes();
			
			citizenService.save(citizenRegistrationDto,imageData);
			return "redirect:/admin/registerCitizen?success";
		} catch (Exception e) {
			return "redirect:/admin/registerCitizen?error";
		}
	}
	
	@GetMapping("/registerCandidate")
	public String showCandidateRegForm(Model model, Principal principal) {
		User currentUser = service.findByUsername(principal.getName());
		model.addAttribute("candidate",new Candidate());
		model.addAttribute("currentUser", currentUser);
		return "add-candidate";
	}
	@PostMapping("/registerCandidate")
	public String registerCitizen(@ModelAttribute("candidate") CandidateRegDto candidate) {
		try {
			candidateService.saveCandidate(candidate);
			return "redirect:/admin/registerCandidate?success";
		} catch (Exception e) {
			return "redirect:/admin/registerCandidate?error";
		}
	}
	
	//delimitation operations
	@GetMapping("/delimPages/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo, Model model,Principal principal) {
		User currentUser = service.findByUsername(principal.getName());
		int pageSize = 10;
		Page<Delimitation> page = delimService.findPagination(pageNo, pageSize);
		List<Delimitation> delims =page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("delims",delims);
		model.addAttribute("delim",new Delimitation());
		model.addAttribute("currentUser", currentUser);
		return "delimitation";
		
	}
	
	@GetMapping("/addDelimitation")
	public String showdelimAddForm(Model model, Principal principal) {
		
		return findPaginated(1,model,principal);
		
	}
	@PostMapping("/addDelimitation")
	public String addDelim(@ModelAttribute("delim") DelimRegDto delim) {
		try {
			delimService.saveDelim(delim);
			return "redirect:/admin/addDelimitation?success";
		} catch (Exception e) {
			return "redirect:/admin/addDelimitation?error";
		}
	}
	@GetMapping("/addDelimitation/{id}")
	public ModelAndView editDelim(@PathVariable(name="id") Long id) {
		ModelAndView editView = new ModelAndView("delimitation");
		Delimitation delim = delimService.findPartyById(id);
		editView.addObject("delim",delim);
		return editView;
	} 
	@GetMapping("/deleteDelim/{id}")
	public String deleteDelim (@PathVariable(name="id") Long id) {
		delimService.deleteDelim(id);
		return "redirect:/admin/addDelimitation";
	} 
//political party operation
	@GetMapping("/addParty")
	public String showPartyAddForm(Model model, Principal principal) {
		User currentUser = service.findByUsername(principal.getName());
		List<Party> parties = partyService.findAll();
		model.addAttribute("party",new PartyRegDto());
		model.addAttribute("parties",parties);
		model.addAttribute("currentUser", currentUser);
		return "political-parties";
	} 
	@GetMapping("/addParty/{id}")
	public ModelAndView showEditPartyForm(@PathVariable(name="id") Long id,  Principal principal, Model model) {
		User currentUser = service.findByUsername(principal.getName());
		ModelAndView editView = new ModelAndView("political-Parties");
		Party party = partyService.findPartyById(id);
		editView.addObject("party",party);
		model.addAttribute("currentUser", currentUser);
		return editView;
	} 
	@GetMapping("/deleteParty/{id}")
	public String deleteParty (@PathVariable(name="id") Long id) {
		partyService.deleteParty(id);
		return "redirect:/admin/addParty";
	} 
	@GetMapping("/images/{id}")
	public void getLogo (@PathVariable(name="id") Long id, HttpServletResponse response) throws IOException {
		byte[] bytes =partyService.findPartyById(id).getLogo();
		InputStream logo = new BufferedInputStream(new ByteArrayInputStream(bytes));
		String mimeType = URLConnection.guessContentTypeFromStream(logo);
		response.setContentType(mimeType);
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	
	} 
	@PostMapping("/addParty")
	public String addParty(@ModelAttribute("party") PartyRegDto party,
			HttpServletRequest request,final @RequestParam("image") MultipartFile file) {
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:  ", uploadDirectory);
			String filename=file.getOriginalFilename();
			String filepath= Paths.get(uploadDirectory,filename).toString();
			log.info("filename: "+ file.getOriginalFilename());
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("folder created");
					dir.mkdir();
				}
				//save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream
						( new File(filepath)));
				stream.write(file.getBytes());
				stream.close();
			}catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData =file.getBytes();
			
			partyService.saveParty(party, imageData);
			return "redirect:/admin/addParty?success";
		} catch (Exception e) {
			return "redirect:/admin/addParty?error";
		}
	}

//political post operation
	@GetMapping("/addPost")
	public String showPostAddForm(Model model, Principal principal) {
		User currentUser = service.findByUsername(principal.getName());
		List<Post> posts = postService.findAll();
		model.addAttribute("post",new PostRegDto());
		model.addAttribute("posts",posts);
		model.addAttribute("currentUser", currentUser);
		return "political-post";
	} 
	@GetMapping("/addPost/{id}")
	public ModelAndView showEditPostForm(@PathVariable(name="id") Long id,  Principal principal, Model model) {
		User currentUser = service.findByUsername(principal.getName());
		ModelAndView editView = new ModelAndView("political-Post");
		Post post = postService.findPostById(id);
		editView.addObject("post",post);
		model.addAttribute("currentUser", currentUser);
		return editView;
	} 
	@GetMapping("/deletePost/{id}")
	public String deletePost (@PathVariable(name="id") Long id) {
		postService.deletePost(id);
		return "redirect:/admin/addPost";
	} 

	@PostMapping("/addPost")
	public String savePost(@ModelAttribute("post") PostRegDto postRegDto) {
		try {
			postService.save(postRegDto);
			return "redirect:/admin/addPost?success";
		} catch (Exception e) {
			return "redirect:/admin/addPost?error";
		}	}
	public String captureFingerprint() {
	 DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
	 capturer.addDataListener(new DPFPDataAdapter() {
		 public void dataAcquired(DPFPDataEvent e) {
		 
		 }
	 });
		return "";
		
	}
}
