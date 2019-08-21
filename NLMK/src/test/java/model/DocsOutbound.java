package model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DocsOutbound extends ForwardingSet<DocOutboundData> {

  private Set<DocOutboundData> delegate;

  public DocsOutbound(DocsOutbound docsOutbound) {
    this.delegate = new HashSet<DocOutboundData>(docsOutbound.delegate);
  }

  public DocsOutbound() {
    this.delegate = new HashSet<DocOutboundData>();
  }

  public DocsOutbound(Collection<DocOutboundData> docsOutbound) {
    this.delegate = new HashSet<DocOutboundData>(docsOutbound);
  }

  @Override
  protected Set<DocOutboundData> delegate() {
    return delegate;
  }

  public DocsOutbound withAdded(DocOutboundData docOutbound) {
    DocsOutbound docsOutbound = new DocsOutbound(this);
    docsOutbound.add(docOutbound);
    return docsOutbound;
  }

  public DocsOutbound without(DocOutboundData docOutbound) {
    DocsOutbound docsOutbound = new DocsOutbound(this);
    docsOutbound.remove(docOutbound);
    return docsOutbound;
  }
}