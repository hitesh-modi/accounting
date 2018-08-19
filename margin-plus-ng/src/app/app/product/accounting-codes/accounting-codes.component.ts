import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ProductService} from "../service/product.service";
import {HSNSectionModel} from "../model/HSNSectionModel";
import {HSNChapterModel} from "../model/HSNChapterModel";
import {ClrDatagridStringFilterInterface, ClrWizard} from "@clr/angular";
import {HSNModel} from "../model/HSNModel";
import {SACHeadingModel} from "../model/SACHeadingModel";
import {SACGroupModel} from "../model/SACGroupModel";
import {SacCodeModel} from "../model/SacCodeModel";

@Component({
  selector: 'app-accounting-codes',
  templateUrl: './accounting-codes.component.html',
  styleUrls: ['./accounting-codes.component.css']
})
export class AccountingCodesComponent implements OnInit {


  @Input("accontingType") accountingType: string;

  @Input("wizardOpen") wizardOpen: boolean;

  @Output("closeWizard") wizardClose: EventEmitter<boolean> = new EventEmitter(false);

  @Output("finishSelection") finishSelection: EventEmitter<string> = new EventEmitter<string>();

  @ViewChild("accountingCodeWizard") accountingCodeWizard: ClrWizard;

  showError: boolean;

  /**
   * values
   */

  hsnSections: Array<HSNSectionModel> = [];

  hsnChapters: Array<HSNChapterModel> = [];

  hsnCodes: Array<HSNModel> = [];

  sacHeadings: Array<SACHeadingModel> = [];

  sacGroups: Array<SACGroupModel> = [];

  sacCodes: Array<SacCodeModel> = [];

  /**
   * selected values
   */

  selectedHsnSection: string = '';

  selectedHsnChapter: string = '';

  selectedHsnCode: string = '';

  selectedSacHeading: string = '';

  selectedSacGroup: string = '';

  selectedSacCode: string = '';

  /**
   * property selected
   */
  hsnSectionSelected: boolean = false;

  hsnChapterSelected: boolean = false;

  hsnCodeSelected: boolean = false;

  sacHeadingSelected: boolean = false;

  sacGroupSelected: boolean = false;

  sacCodeSelected: boolean = false;

  /**
   * Filters
   */
  sectionFilter: SectionFilter = new SectionFilter();

  chapterFilter: ChapterFilter = new ChapterFilter();

  hsnFilter: HSNFilter = new HSNFilter();

  headingFilter: SACHeadingFilter = new SACHeadingFilter();

  groupFilter: SACGroupFilter = new SACGroupFilter();

  sacFilter: SACCodeFilter = new SACCodeFilter();

  constructor(private productService: ProductService) { }

  ngOnInit() {

    if(this.accountingType == 'Good') {
      this.productService.getHSNSections().subscribe(
        onloadeddata => {
          this.hsnSections = onloadeddata;
        }
      );
    }
    if(this.accountingType == 'Service') {
      this.productService.getSACHeadings().subscribe(
        onloadeddata => {
          this.sacHeadings = onloadeddata;
        }
      );
    }
  }

  closeWizard() {
    this.wizardClose.emit(false);
  }

  /**
   * Page Getters
   */
  getChaptersPage(): void {
    this.fetchChapters();
    this.accountingCodeWizard.next();
  }

  getHSNPage(): void {
    this.fetchHsnCodes();
    this.accountingCodeWizard.next();
  }

  getGroupsPage(): void {
    this.fetchSacGroups();
    this.accountingCodeWizard.next();
  }

  getSACPage(): void {
    this.fetchSacCodes();
    this.accountingCodeWizard.next();
  }


  /**
   * Fetch methods
   */
  private fetchChapters(): void{
    this.productService.getHSNChapters(this.selectedHsnSection).subscribe(onloadeddata => {
      this.hsnChapters = onloadeddata;
    });
  }

  private fetchHsnCodes(): void {
    this.productService.getHSNCodes(this.selectedHsnChapter).subscribe(onloadeddata => {
      this.hsnCodes = onloadeddata;
    });
  }

  private fetchSacGroups(): void {
    this.productService.getSACGroups(this.selectedSacHeading).subscribe(onloadeddata => {
      this.sacGroups = onloadeddata;
      console.log('Sac Groups', this.sacGroups);
    });
  }

  private fetchSacCodes(): void {
    this.productService.getSACCodes(this.selectedSacGroup).subscribe(onloadeddata => {
      this.sacCodes = onloadeddata;
    });
  }

  /**
   * Set selections
   */

  setSelectedChapter(chapterId: string): void {
    this.selectedHsnChapter = chapterId;
    this.hsnChapterSelected = true;
  }

  setSelectedSection(sectionId: string): void {
    this.selectedHsnSection = sectionId;
    this.hsnSectionSelected = true;
  }

  setSelectedHsn(hsn: string): void {
    this.selectedHsnCode = hsn;
    this.hsnCodeSelected = true;
  }

  setSelectedHeading(heading: string): void {
    this.selectedSacHeading = heading;
    this.sacHeadingSelected = true;
  }
  setSelectedGroup(group: string): void {
    this.selectedSacGroup = group;
    this.sacGroupSelected = true;
  }
  setSelectedCode(code: string): void {
    this.selectedSacCode = code;
    this.sacCodeSelected = true;
  }

  /**
   * Finish Methods
   */
  finishHSNSelection() {
    this.finishSelection.emit(this.selectedHsnCode);
    this.closeWizard();
  }

  finishSacSelection() {
    this.finishSelection.emit(this.selectedSacCode);
    this.closeWizard();
  }

}

class SectionFilter implements ClrDatagridStringFilterInterface<HSNSectionModel> {
  accepts(section: HSNSectionModel, search: string):boolean {
    return section.sectionDesc.toLowerCase().indexOf(search.toLowerCase()) >= 0;
  }
}

class ChapterFilter implements ClrDatagridStringFilterInterface<HSNChapterModel> {
  accepts(chapter: HSNChapterModel, search: string):boolean {
    return chapter.chapterDesc.toLowerCase().indexOf(search.toLowerCase()) >= 0;
  }
}

class HSNFilter implements ClrDatagridStringFilterInterface<HSNModel> {
  accepts(chapter: HSNModel, search: string):boolean {
    return chapter.hsnDesc.toLowerCase().indexOf(search.toLowerCase()) >= 0;
  }
}

class SACHeadingFilter implements ClrDatagridStringFilterInterface<SACHeadingModel> {
  accepts(sacHeading: SACHeadingModel, search: string):boolean {
    return sacHeading.headingDesc.toLowerCase().indexOf(search.toLowerCase()) >= 0;
  }
}

class SACGroupFilter implements ClrDatagridStringFilterInterface<SACGroupModel> {
  accepts(sacGroup: SACGroupModel, search: string):boolean {
    return sacGroup.groupDesc.toLowerCase().indexOf(search.toLowerCase()) >= 0;
  }
}


class SACCodeFilter implements ClrDatagridStringFilterInterface<SacCodeModel> {
  accepts(sac: SacCodeModel, search: string):boolean {
    return sac.sacDesc.toLowerCase().indexOf(search.toLowerCase()) >= 0;
  }
}


