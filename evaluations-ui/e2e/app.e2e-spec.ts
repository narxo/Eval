import { EvaluationsUiPage } from './app.po';

describe('evaluations-ui App', () => {
  let page: EvaluationsUiPage;

  beforeEach(() => {
    page = new EvaluationsUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
